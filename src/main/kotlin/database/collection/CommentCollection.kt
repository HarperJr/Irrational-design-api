package database.collection

import database.collection.impl.DocumentCollection
import database.document.Comment
import database.document.Post
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId

class CommentCollection(private val collection: CoroutineCollection<Comment>) :
    DocumentCollection<Comment>(collection) {
    suspend fun getAllByPost(postId: String): List<Comment> {
        return collection.find(Comment::postId eq ObjectId(postId).toId()).toList()
    }

    suspend fun countByPost(postId: Id<Post>): Long {
        return collection.countDocuments(Comment::postId eq postId)
    }
}
