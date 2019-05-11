package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.Bookmark
import database.document.Post
import org.litote.kmongo.Id
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class BookmarkCollection(private val collection: CoroutineCollection<Bookmark>) :
    DocumentCollection<Bookmark>(collection) {

    suspend fun countByPost(postId: Id<Post>): Long {
        return collection.countDocuments(Bookmark::postId eq postId)
    }

    suspend fun bookmarked(postId: Id<Post>, artistId: Id<Artist>): Boolean {
        return collection.findOne(and(Bookmark::postId eq postId, Bookmark::artistId eq artistId)) != null
    }

    suspend fun deleteByArtist(postId: Id<Post>, artistId: Id<Artist>) {
        collection.deleteOne(and(Bookmark::postId eq postId, Bookmark::artistId eq artistId))
    }

}
