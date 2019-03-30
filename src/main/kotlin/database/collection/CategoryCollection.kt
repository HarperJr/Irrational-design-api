package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Category

class CategoryCollection(private val collection: MongoCollection<Category>) :
    DocumentCollection<Category>(collection) {

}
