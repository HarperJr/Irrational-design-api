package interactor.art

import database.collection.ArtCollection
import database.document.Art
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtLoaderImpl @Inject constructor(
    private val artCollection: ArtCollection
) : ArtLoader {

    override suspend fun insert(arts: List<Art>) = coroutineScope {
        withContext(Dispatchers.IO) {
            artCollection.insert(arts)
        }
    }
}