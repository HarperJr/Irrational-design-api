package database.collection

import database.collection.impl.DocumentCollection
import database.document.CategoryInPost
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class CategoryInPostCollection(private val collection: CoroutineCollection<CategoryInPost>) :
    DocumentCollection<CategoryInPost>(collection) {

    suspend fun findByPost(postId: String) = collection
        .find(CategoryInPost::postId eq postId)
        .toList()
        .map { it.postId }
}

