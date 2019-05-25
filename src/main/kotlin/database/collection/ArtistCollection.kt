package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class ArtistCollection(private val collection: CoroutineCollection<Artist>) :
    DocumentCollection<Artist>(collection) {

    suspend fun findByName(name: String) = collection.findOne(Artist::name eq name)

    suspend fun findEx(id: Id<Artist>): Artist? {
        return collection.findOne(Artist::id eq id)
    }
}
