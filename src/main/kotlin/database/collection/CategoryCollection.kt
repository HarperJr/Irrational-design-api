package database.collection

import database.collection.impl.DocumentCollection
import database.document.Category
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class CategoryCollection(private val collection: CoroutineCollection<Category>) :
    DocumentCollection<Category>(collection) {

    suspend fun findByName(name: String): Category? {
        return collection.findOne(Category::name eq name)
    }
}
