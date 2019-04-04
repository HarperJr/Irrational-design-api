package interactor.artist

import database.collection.ArtistCollection
import database.collection.AvatarCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import response.ArtistResponse
import javax.inject.Inject

class ArtistLoaderImpl @Inject constructor(
    private val artistCollection: ArtistCollection,
    private val avatarCollection: AvatarCollection
) : ArtistLoader {
    override suspend fun artist(id: String): ArtistResponse? = coroutineScope {
        withContext(Dispatchers.IO) {
            null
        }
    }
}