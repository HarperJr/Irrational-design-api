package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Comment

class CommentCollection(private val collection: MongoCollection<Comment>) :
    DocumentCollection<Comment>(collection) {

}
