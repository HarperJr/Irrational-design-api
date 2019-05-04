package interactor.art

import database.collection.ArtCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtLoaderImpl @Inject constructor(
    private val artCollection: ArtCollection
) : ArtLoader {

    override suspend fun save(bytes: ByteArray) = coroutineScope {
        withContext(Dispatchers.IO) {

        }
    }
}