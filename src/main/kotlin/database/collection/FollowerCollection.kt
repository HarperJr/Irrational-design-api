package database.collection

import database.collection.impl.DocumentCollection
import database.document.Follower
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class FollowerCollection(private val collection: CoroutineCollection<Follower>) :
    DocumentCollection<Follower>(collection) {

    suspend fun deleteByFollower(followerId: Id<Follower>) {
        collection.deleteOne(Follower::id eq followerId)
    }

    suspend fun followed(followerId: Id<Follower>): Boolean {
        return collection.findOne(Follower::id eq followerId) != null
    }
}
