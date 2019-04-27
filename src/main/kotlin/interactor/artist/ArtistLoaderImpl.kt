package interactor.artist

import database.collection.ArtistCollection
import database.collection.AvatarCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import response.ArtistResponse
import response.AvatarResponse
import javax.inject.Inject

class ArtistLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : ArtistLoader {
    override suspend fun artist(id: String): ArtistResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            val artist = artistCollection.find(id.toId()) ?: return@withContext null
            val avatar = avatarCollection.find(artist.avatarId)!!
            return@withContext ArtistResponse(
                avatar = AvatarResponse(
                    link = avatar.link
                ),
                permalink = artist.permalink,
                name = artist.name,
                email = artist.email,
                followed = false,
                id = artist.id
            )
        }
    }
}