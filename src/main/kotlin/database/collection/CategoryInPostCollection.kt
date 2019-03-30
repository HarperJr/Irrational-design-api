package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.CategoryInPost

class CategoryInPostCollection(private val collection: MongoCollection<CategoryInPost>) :
    DocumentCollection<CategoryInPost>(collection) {

}

