package database.collection

import com.mongodb.client.MongoCollection
import database.collection.impl.DocumentCollection
import database.entity.Avatar

class AvatarCollection(private val collection: MongoCollection<Avatar>) :
    DocumentCollection<Avatar>(collection) {

}
