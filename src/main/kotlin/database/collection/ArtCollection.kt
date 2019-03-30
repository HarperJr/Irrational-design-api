package database.collection

import database.collection.impl.DocumentCollection
import database.document.Art
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ArtCollection(private val collection: CoroutineCollection<Art>) :
    DocumentCollection<Art>(collection) {

    suspend fun getByPost(postId: String): List<Art> = collection.find(Art::postId eq postId).toList()
}