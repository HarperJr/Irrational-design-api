package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Art

class ArtCollection(private val collection: MongoCollection<Art>) :
    DocumentCollection<Art>(collection) {

}