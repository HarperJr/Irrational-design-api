package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Post
import com.irrational.database.document.Preview
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class PreviewCollection(private val collection: CoroutineCollection<Preview>) :
    DocumentCollection<Preview>(collection) {

    suspend fun findByPost(id: Id<Post>) = collection.findOne(Preview::postId eq id)
}