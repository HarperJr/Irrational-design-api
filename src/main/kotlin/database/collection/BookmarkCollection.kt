package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import database.document.Bookmark
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.toId

class BookmarkCollection(private val collection: CoroutineCollection<Bookmark>) :
    DocumentCollection<Bookmark>(collection) {

    suspend fun countByPost(postId: String): Long {
        return collection.countDocuments(Bookmark::postId eq postId.toId())
    }

    suspend fun bookmarked(artistId: Id<Artist>): Boolean {
        return collection.findOne(Bookmark::artistId eq artistId) != null
    }

    suspend fun deleteByArtist(artistId: Id<Artist>) {
        collection.deleteOne(Bookmark::artistId eq artistId)
    }

}
