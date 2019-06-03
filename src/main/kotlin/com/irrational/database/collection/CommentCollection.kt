package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Comment
import com.irrational.database.document.Post
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class CommentCollection(private val collection: CoroutineCollection<Comment>) :
    DocumentCollection<Comment>(collection) {
    suspend fun allByPost(postId: Id<Post>): List<Comment> {
        return collection.find(Comment::postId eq ObjectId("$postId")).toList()
    }

    suspend fun countByPost(postId: Id<Post>): Long {
        return collection.countDocuments(Comment::postId eq ObjectId("$postId"))
    }
}
