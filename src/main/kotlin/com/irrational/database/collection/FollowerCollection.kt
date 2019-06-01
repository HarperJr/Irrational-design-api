package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Artist
import com.irrational.database.document.Follower
import org.litote.kmongo.Id
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class FollowerCollection(private val collection: CoroutineCollection<Follower>) :
    DocumentCollection<Follower>(collection) {

    suspend fun deleteByFollower(followerId: Id<Artist>) {
        collection.deleteOne(Follower::followerId eq followerId)
    }

    suspend fun followed(artistId: Id<Artist>, followerId: Id<Artist>): Boolean {
        return collection.findOne(and(Follower::artistId eq artistId, Follower::followerId eq followerId)) != null
    }

    suspend fun followers(artistId: Id<Artist>): List<Follower> {
        return collection.find(Follower::artistId eq artistId).toList()
    }

    suspend fun follows(artistId: Id<Artist>): List<Follower> {
        return collection.find(Follower::followerId eq artistId).toList()
    }
}