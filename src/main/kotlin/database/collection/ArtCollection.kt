package database.collection

import database.collection.impl.DocumentCollection
import database.document.Art
import database.document.Post
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ArtCollection(private val collection: CoroutineCollection<Art>) :
    DocumentCollection<Art>(collection) {

    suspend fun findByPost(postId: Id<Post>): List<Art> = collection.find(Art::postId eq postId).toList()
}