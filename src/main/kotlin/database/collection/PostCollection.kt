package database.collection

import database.collection.impl.DocumentCollection
import database.document.Post
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.group
import org.litote.kmongo.gt
import org.litote.kmongo.lookup
import org.litote.kmongo.unwind
import java.util.*

class PostCollection(private val collection: CoroutineCollection<Post>) :
    DocumentCollection<Post>(collection) {

    suspend fun allWithBoundary(from: Int, to: Int): List<Post> = collection.find().skip(from).limit(to).toList()

    suspend fun hotWithBoundary(from: Int, to: Int): List<Post> {
        val actualDate = Calendar.getInstance().let {
            it.add(Calendar.DAY_OF_MONTH, -7)
            it.timeInMillis
        }

        return collection.find(Post::date gt actualDate).toList()
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
                newAs = "rated"
            ),
            unwind("rated"),
            group(Post::id)
        ).toList()

    }

    suspend fun popularWithBoundary(from: Int, to: Int): List<Post> {
        return emptyList()
    }
}
