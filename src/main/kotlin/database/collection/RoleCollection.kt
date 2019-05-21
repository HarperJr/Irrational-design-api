package database.collection

import database.collection.impl.DocumentCollection
import database.document.Role
import database.document.RoleType
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class RoleCollection(private val collection: CoroutineCollection<Role>) :
    DocumentCollection<Role>(collection) {

    suspend fun findByType(type: RoleType): Role? = collection.findOne(Role::type eq type)
}