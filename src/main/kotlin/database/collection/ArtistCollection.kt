package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Artist

class ArtistCollection(private val collection: MongoCollection<Artist>) :
    DocumentCollection<Artist>(collection) {


}
