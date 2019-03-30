package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Post

class PostCollection(private val collection: MongoCollection<Post>) :
    DocumentCollection<Post>(collection) {

}
