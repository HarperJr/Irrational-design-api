package interactor.user

import PwdEncryptor
import database.collection.UserCollection
import database.document.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.toId
import javax.inject.Inject

class UserLoaderImpl @Inject constructor(
    private val userCollection: UserCollection
) : UserLoader {

    override suspend fun findByCredentials(name: String, password: String): User? = coroutineScope {
        withContext(Dispatchers.IO) {
            userCollection.findByName(name)?.let { user ->
                if (PwdEncryptor.check(password, user.password)) user else null
            }
        }
    }

    override suspend fun find(id: String): User? = coroutineScope {
        withContext(Dispatchers.IO) {
            userCollection.find(id.toId())
        }
    }

    override suspend fun insert(user: User) = coroutineScope {
        withContext(Dispatchers.IO) {
            if (userCollection.findByName(user.name) == null) {
                user.apply { password = PwdEncryptor.encrypt(password) }
                userCollection.insert(user)
            } else throw Exception("Invalid arguments") //Avoid those exceptions
        }
    }
}