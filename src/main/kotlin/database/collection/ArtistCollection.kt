package database.collection

import database.collection.impl.DocumentCollection
import database.document.Artist
import org.litote.kmongo.coroutine.CoroutineCollection

class ArtistCollection(private val collection: CoroutineCollection<Artist>) :
    DocumentCollection<Artist>(collection) {

}
