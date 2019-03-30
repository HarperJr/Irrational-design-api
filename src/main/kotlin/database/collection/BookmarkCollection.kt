package database.collection

import database.collection.impl.DocumentCollection
import database.document.Bookmark
import org.litote.kmongo.coroutine.CoroutineCollection

class BookmarkCollection(private val collection: CoroutineCollection<Bookmark>) :
    DocumentCollection<Bookmark>(collection) {

}
