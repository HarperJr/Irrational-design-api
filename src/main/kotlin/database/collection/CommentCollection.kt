package database.collection

import database.collection.impl.DocumentCollection
import database.document.Comment
import org.litote.kmongo.coroutine.CoroutineCollection

class CommentCollection(private val collection: CoroutineCollection<Comment>) :
    DocumentCollection<Comment>(collection) {

}
