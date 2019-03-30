package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Tag

class TagCollection(private val collection: MongoCollection<Tag>) :
    DocumentCollection<Tag>(collection) {

}