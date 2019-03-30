package database.collection

import database.collection.impl.DocumentCollection
import database.document.Tag
import org.litote.kmongo.coroutine.CoroutineCollection

class TagCollection(private val collection: CoroutineCollection<Tag>) :
    DocumentCollection<Tag>(collection) {

}