package database.collection

import database.collection.impl.DocumentCollection
import database.document.Post
import org.litote.kmongo.coroutine.CoroutineCollection

class PostCollection(private val collection: CoroutineCollection<Post>) :
    DocumentCollection<Post>(collection) {
}
