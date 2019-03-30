package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.TagInPost

class TagInPostCollection(private val collection: MongoCollection<TagInPost>) :
    DocumentCollection<TagInPost>(collection) {

}
