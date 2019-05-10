package interactor.artist

import PwdEncryptor
import database.collection.ArtistCollection
import database.collection.AvatarCollection
import database.collection.FollowerCollection
import database.document.Artist
import database.document.Follower
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.ArtistResponse
import response.AvatarResponse
import java.util.*
import javax.inject.Inject

class ArtistLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection,
    private val followerCollection: FollowerCollection
) : ArtistLoader {

    override suspend fun artist(id: String): ArtistResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(id.toId())
            if (artist == null) {
                throw Exception("Unable to find artist with id $id")
            } else {
                val avatar = artist.avatarId?.let { avatarCollection.find(it) }
                return@withContext ArtistResponse(
                    avatar = avatar?.let {
                        AvatarResponse(it.link)
                    },
                    name = artist.name,
                    email = artist.email,
                    followed = false,
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

    override suspend fun insert(name: String, password: String, email: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            if (artistCollection.findByName(name) == null) {
                artistCollection.insert(
                    Artist(
                        name = name,
                        password = PwdEncryptor.encrypt(password),
                        email = email,
                        registered = Date().time
                    )
                )
            } else throw Exception("Invalid arguments") //Avoid those exceptions
        }
    }

    override suspend fun follow(followerId: String, artistId: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(artistId.toId())
            if (artist == null) {
                throw Exception("Unable to find artist with id $artistId")
            } else {
                if (followerCollection.followed(followerId.toId())) {
                    throw Exception("Already followed")
                }
                followerCollection.insert(
                    Follower(
                        followerId = followerId.toId(),
                        artistId = artist.id
                    )
                )
            }
        }
    }

    override suspend fun unfollow(followerId: String, artistId: String) = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(artistId.toId())
            if (artist == null) {
                throw Exception("Unable to find artist with id $artistId")
            } else {
                followerCollection.deleteByFollower(followerId.toId())
            }
        }
    }
}