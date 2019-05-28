package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Payment
import org.litote.kmongo.coroutine.CoroutineCollection

class PaymentCollection(private val collection: CoroutineCollection<Payment>) :
    DocumentCollection<Payment>(collection) {

//    suspend fun findByRequestId(requestId: String) = collection.findOne(Payment::requestId eq requestId)
}