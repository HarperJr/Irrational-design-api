package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Art
import com.irrational.database.document.Post
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ArtCollection(private val collection: CoroutineCollection<Art>) :
    DocumentCollection<Art>(collection) {

    suspend fun findByPost(postId: Id<Post>): List<Art> = collection.find(
        Art::postId eq postId).toList()

    suspend fun deleteByPost(postId: Id<Post>) {
        collection.deleteMany(Art::postId eq postId)
    }
}