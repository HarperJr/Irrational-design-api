package com.irrational.interactor.artist

import com.irrational.FileManager
import com.irrational.ImageFile
import com.irrational.PwdEncryptor
import com.irrational.database.collection.ArtistCollection
import com.irrational.database.collection.AvatarCollection
import com.irrational.database.collection.FollowerCollection
import com.irrational.database.collection.RoleCollection
import com.irrational.database.document.Artist
import com.irrational.database.document.Avatar
import com.irrational.database.document.Follower
import com.irrational.response.*
import com.irrational.utils.ApiException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import javax.inject.Inject

class ArtistLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val followerCollection: FollowerCollection,
    private val roleCollection: RoleCollection
) : ArtistLoader {

    override suspend fun artist(id: String): ArtistResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(id.toId())
            if (artist == null) {
                throw ApiException(
                    errorMessage = "Unable to find artist with id $id",
                    statusCode = HttpStatusCode.BadRequest)
            } else {
                val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                return@withContext ArtistResponse(
                    avatar = avatar?.let {
                        AvatarResponse(it.link)
                    },
                    name = artist.name,
                    email = artist.email,
                    followed = false,
                    followers = followerCollection.followers(artist.id).count(),
                    follows = followerCollection.follows(artist.id).count(),
                    id = artist.id
                )
            }
        }
    }

    override suspend fun findByCredentials(name: String, password: String): Artist? = coroutineScope {
        withContext(Dispatchers.IO) {
            artistCollection.findByName(name)?.let { user ->
                if (PwdEncryptor.check(password, user.password)) user else null
            }
        }
    }

    override suspend fun find(id: String): Artist? = coroutineScope {
        withContext(Dispatchers.IO) {
            artistCollection.find(id.toId())
        }
    }

    override suspend fun followers(artistId: String): FollowersResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            FollowersResponse(
                followerCollection.followers(artistId.toId())
                    .map { follower ->
                        val artist = artistCollection.find(follower.artistId)!!
                        val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                        ArtistResponse(
                            id = artist.id,
                            name = artist.name,
                            followed = false,
                            followers = followerCollection.followers(artist.id).count(),
                            follows = followerCollection.follows(artist.id).count(),
                            email = artist.email,
                            avatar = avatar?.let { AvatarResponse(it.link) }
                        )
                    }
            )
        }
    }

    override suspend fun follows(artistId: String): FollowsResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            FollowsResponse(
                followerCollection.follows(artistId.toId())
                    .map { follower ->
                        val artist = artistCollection.find(follower.artistId)!!
                        val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                        ArtistResponse(
                            id = artist.id,
                            name = artist.name,
                            followed = false,
                            followers = followerCollection.followers(artist.id).count(),
                            follows = followerCollection.follows(artist.id).count(),
                            email = artist.email,
                            avatar = avatar?.let { AvatarResponse(it.link) }
                        )
                    }
            )
        }
    }

    override suspend fun followed(artistId: String, id: String): FollowedResponse = coroutineScope {
        withContext(Dispatchers.IO) {
            FollowedResponse(followerCollection.followed(id.toId(), artistId.toId()))
        }
    }

    override suspend fun insert(name: String, password: String, email: String, avatarFile: ImageFile?) = coroutineScope {
        withContext(Dispatchers.IO) {
            if (artistCollection.findByName(name) == null) {
                val avatar = avatarFile?.let { image ->
                    val avatarFolder = name.toLowerCase()
                    val avatarName = "$avatarFolder/${image.name}"
                    FileManager.save(FileManager.avatarsFolder(), avatarName, image.bytes)
                    Avatar(avatarName)
                }
                avatar?.let { avatarCollection.insert(it) }
                artistCollection.insert(
                    Artist(
                        name = name,
                        password = PwdEncryptor.hash(password),
                        email = email,
                        roleId = null,
                        avatarId = avatar?.id
                    )
                )
            } else throw Exception("Invalid arguments")
        }
    }

    override suspend fun follow(followerId: String, artistId: String, initial: Boolean) = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(artistId.toId())
            if (artist == null) {
                throw Exception("Unable to find artist with id $artistId")
            } else {
                val followed = followerCollection.followed(artistId.toId(), followerId.toId())
                if (initial) {
                    if (followed) throw Exception("Already followed")
                    followerCollection.insert(
                        Follower(
                            followerId = followerId.toId(),
                            artistId = artist.id
                        )
                    )
                } else {
                    if (!followed) return@withContext
                    followerCollection.deleteByFollower(followerId.toId())
                }
            }
        }
    }
}