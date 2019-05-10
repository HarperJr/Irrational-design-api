package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.Like
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.toId

class LikeCollection(private val collection: CoroutineCollection<Like>) :
    DocumentCollection<Like>(collection) {

    suspend fun countByPost(postId: String): Long {
        return collection.countDocuments(Like::postId eq postId.toId())
    }

    suspend fun liked(artistId: Id<Artist>): Boolean {
        return collection.findOne(Like::artistId eq artistId) != null
    }

    suspend fun deleteByArtist(artistId: Id<Artist>) {
        collection.deleteOne(Like::artistId eq artistId)
    }
}
