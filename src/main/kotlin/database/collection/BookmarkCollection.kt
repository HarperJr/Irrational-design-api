package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Bookmark

class BookmarkCollection(private val collection: MongoCollection<Bookmark>) :
    DocumentCollection<Bookmark>(collection) {

}
