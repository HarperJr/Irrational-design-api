package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Post
import com.irrational.database.document.TagInPost
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class TagInPostCollection(private val collection: CoroutineCollection<TagInPost>) :
    DocumentCollection<TagInPost>(collection) {

    suspend fun findByPost(postId: Id<Post>) = collection
        .find(TagInPost::postId eq postId)
        .toList()
        .map { it.tagId }

    suspend fun findAllByPost(postId: Id<Post>) = collection
        .find(TagInPost::postId eq postId)
        .toList()

    suspend fun deleteByPost(postId: Id<Post>) {
        collection.deleteMany(TagInPost::postId eq postId)
    }
}
