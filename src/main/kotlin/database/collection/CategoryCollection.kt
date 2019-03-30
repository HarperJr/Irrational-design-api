package database.collection

import database.collection.impl.DocumentCollection
import database.document.Category
import org.litote.kmongo.coroutine.CoroutineCollection

class CategoryCollection(private val collection: CoroutineCollection<Category>) :
    DocumentCollection<Category>(collection) {

}
