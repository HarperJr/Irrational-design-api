package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.CategoryInPost
import com.irrational.database.document.Post
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class CategoryInPostCollection(private val collection: CoroutineCollection<CategoryInPost>) :
    DocumentCollection<CategoryInPost>(collection) {

    suspend fun findByPost(postId: Id<Post>) = collection
        .find(CategoryInPost::postId eq postId)
        .toList()
        .map { it.categoryId }

    suspend fun findAllByPost(postId: Id<Post>) = collection
        .find(CategoryInPost::postId eq postId)
        .toList()

    suspend fun deleteByPost(postId: Id<Post>) {
        collection.deleteMany(CategoryInPost::postId eq postId)
    }
}

