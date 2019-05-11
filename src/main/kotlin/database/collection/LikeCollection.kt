package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.Like
import database.document.Post
import org.litote.kmongo.Id
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class LikeCollection(private val collection: CoroutineCollection<Like>) :
    DocumentCollection<Like>(collection) {

    suspend fun countByPost(postId: Id<Post>): Long {
        return collection.countDocuments(Like::postId eq postId)
    }

    suspend fun liked(postId: Id<Post>, artistId: Id<Artist>): Boolean {
        return collection.findOne(and(Like::postId eq postId, Like::artistId eq artistId)) != null
    }

    suspend fun deleteByArtist(postId: Id<Post>, artistId: Id<Artist>) {
        collection.deleteOne(and(Like::postId eq postId, Like::artistId eq artistId))
    }
}
