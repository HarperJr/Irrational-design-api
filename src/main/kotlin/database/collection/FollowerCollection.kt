package database.collection

import database.collection.impl.DocumentCollection
import database.document.Follower
import org.litote.kmongo.coroutine.CoroutineCollection

class FollowerCollection(private val collection: CoroutineCollection<Follower>) :
    DocumentCollection<Follower>(collection) {

}
