package database.collection

import database.collection.impl.DocumentCollection
import database.document.Comment
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class CommentCollection(private val collection: CoroutineCollection<Comment>) :
    DocumentCollection<Comment>(collection) {
    suspend fun getAllByPost(postId: String): List<Comment> {
        return collection.find(Comment::postId eq postId).toList()
    }
}
