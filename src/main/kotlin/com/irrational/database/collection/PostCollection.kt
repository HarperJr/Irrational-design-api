package com.irrational.database.collection

import com.irrational.database.collection.impl.DocumentCollection
import com.irrational.database.document.Artist
import com.irrational.database.document.Post
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate

class PostCollection(private val collection: CoroutineCollection<Post>) :
    DocumentCollection<Post>(collection) {

    suspend fun allWithBoundary(from: Int, to: Int): List<Post> {
        return collection.find(Post::blocked eq false).skip(from).limit(to).toList()
    }

    suspend fun hotWithBoundary(from: Int, to: Int): List<Post> {
        return allWithBoundary(from, to)
    }

    suspend fun recommendedWithBoundary(from: Int, to: Int): List<Post> {
        return allWithBoundary(from, to)
    }

    suspend fun viewedWithBoundary(from: Int, to: Int): List<Post> {
        return allWithBoundary(from, to)
    }

    suspend fun ratedWithBoundary(from: Int, to: Int): List<Post> {
        return collection.aggregate<Post>(
            lookup(
                from = "like",
                localField = "id",
                foreignField = "postId",
                newAs = "like"
            ),
            unwind("like"),
            group(Post::id)
        ).toList()

    }

    suspend fun popularWithBoundary(from: Int, to: Int): List<Post> {
        return emptyList()
    }

    suspend fun byArtistWithBoundary(from: Int, to: Int, artistId: Id<Artist>): List<Post> {
        return collection.find(
            and(
                Post::artistId eq ObjectId("$artistId"),
                Post::blocked eq false
            )
        ).skip(from).limit(to).toList()
    }
}
