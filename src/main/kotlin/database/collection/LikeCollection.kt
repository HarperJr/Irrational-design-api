package database.collection

import database.collection.impl.DocumentCollection
import database.document.Like
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.toId

class LikeCollection(private val collection: CoroutineCollection<Like>) :
    DocumentCollection<Like>(collection) {

    suspend fun countByPost(postId: String): Long {
        return collection.countDocuments(Like::postId eq postId.toId())
    }
}
