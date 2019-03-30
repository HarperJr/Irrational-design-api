package database.collection

import database.collection.impl.DocumentCollection
import database.document.Avatar
import org.litote.kmongo.coroutine.CoroutineCollection

class AvatarCollection(private val collection: CoroutineCollection<Avatar>) :
    DocumentCollection<Avatar>(collection) {

}
