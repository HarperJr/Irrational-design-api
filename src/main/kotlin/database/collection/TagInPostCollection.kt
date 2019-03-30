package database.collection

import database.collection.impl.DocumentCollection
import database.document.TagInPost
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class TagInPostCollection(private val collection: CoroutineCollection<TagInPost>) :
    DocumentCollection<TagInPost>(collection) {

    suspend fun findByPost(postId: String) = collection
        .find(TagInPost::postId eq postId)
        .toList()
        .map { it.tagId }
}
