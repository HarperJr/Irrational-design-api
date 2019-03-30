package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Follower

class FollowerCollection(private val collection: MongoCollection<Follower>) :
    DocumentCollection<Follower>(collection) {

}
