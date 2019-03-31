package database.collection

import database.collection.impl.DocumentCollection
import database.document.Preview
import org.litote.kmongo.coroutine.CoroutineCollection

class PreviewCollection(private val collection: CoroutineCollection<Preview>) :
    DocumentCollection<Preview>(collection) {
}