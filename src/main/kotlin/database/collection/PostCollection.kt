package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.Post
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate

class PostCollection(private val collection: CoroutineCollection<Post>) :
    DocumentCollection<Post>(collection) {

    suspend fun allWithBoundary(from: Int, to: Int): List<Post> {
        return collection.find().skip(from).limit(to).toList()
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
        return collection.find(Post::artistId eq artistId).skip(from).limit(to).toList()
    }
}
