package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Artist
import com.irrational.database.document.Like
import com.irrational.database.document.Post
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class LikeCollection(private val collection: CoroutineCollection<Like>) :
    DocumentCollection<Like>(collection) {

    suspend fun countByPost(postId: Id<Post>): Long {
        return collection.countDocuments(Like::postId eq ObjectId("$postId"))
    }

    suspend fun liked(postId: Id<Post>, artistId: Id<Artist>): Boolean {
        return collection.findOne(
            and(
                Like::postId eq ObjectId("$postId"),
                Like::artistId eq ObjectId("$artistId")
            )
        ) != null
    }

    suspend fun deleteByArtist(postId: Id<Post>, artistId: Id<Artist>) {
        collection.deleteOne(
            and(
                Like::postId eq ObjectId("$postId"),
                Like::artistId eq ObjectId("$artistId")
            )
        )
    }
}
