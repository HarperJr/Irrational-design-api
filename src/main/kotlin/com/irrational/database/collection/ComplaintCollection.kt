package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Complaint
import com.irrational.database.document.Post
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ComplaintCollection(private val collection: CoroutineCollection<Complaint>) :
    DocumentCollection<Complaint>(collection) {

    suspend fun findByPost(postId: Id<Post>): List<Complaint> = collection.find(
        Complaint::post eq ObjectId("$postId")
    ).toList()
}