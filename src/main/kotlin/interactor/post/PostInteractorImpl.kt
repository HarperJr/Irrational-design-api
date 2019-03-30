package interactor.post

import database.collection.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import response.PostResponse
import java.text.SimpleDateFormat
import javax.inject.Inject

class PostInteractorImpl @Inject constructor(
    private val postCollection: PostCollection,
    private val artistCollection: ArtistCollection,
    private val artCollection: ArtCollection,
    private val tagCollection: TagCollection,
    private val categoryCollection: CategoryCollection,
    private val tagInPostCollection: TagInPostCollection,
    private val categoryInPostCollection: CategoryInPostCollection
) : PostInteractor {

    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")

    override suspend fun post(id: String): PostResponse? = coroutineScope {
        async {
           null
        }.await()
    }
}