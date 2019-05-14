package database.collection

import database.collection.impl.DocumentCollection
import database.document.PaymentSource
import org.litote.kmongo.coroutine.CoroutineCollection

class PaymentSourceCollection(private val collection: CoroutineCollection<PaymentSource>) :
    DocumentCollection<PaymentSource>(collection) {
}