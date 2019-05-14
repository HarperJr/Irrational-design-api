package database.collection

import database.collection.impl.DocumentCollection
import database.document.Payment
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class PaymentCollection(private val collection: CoroutineCollection<Payment>) :
    DocumentCollection<Payment>(collection) {

    suspend fun findByRequestId(requestId: String) = collection.findOne(Payment::requestId eq requestId)
}