package interactor.user

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

    override suspend fun identify(name: String, password: String): User? = coroutineScope {
        withContext(Dispatchers.IO) {
            userCollection.identify(name, password)
        }
    }

    override suspend fun find(id: String): User? = coroutineScope {
        withContext(Dispatchers.IO) {
            userCollection.find(id.toId())
        }
    }
}