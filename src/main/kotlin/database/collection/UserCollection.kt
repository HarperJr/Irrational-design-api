package database.collection

import database.collection.impl.DocumentCollection
import database.document.User
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class UserCollection(private val collection: CoroutineCollection<User>) :
    DocumentCollection<User>(collection) {

    suspend fun validate(name: String, password: String) = collection.findOne(and(User::name eq name, User::password eq password)) != null
}