package database.collection

import database.collection.impl.DocumentCollection
import database.document.Payment
import org.litote.kmongo.coroutine.CoroutineCollection

class PaymentCollection(private val collection: CoroutineCollection<Payment>) :
    DocumentCollection<Payment>(collection) {
}