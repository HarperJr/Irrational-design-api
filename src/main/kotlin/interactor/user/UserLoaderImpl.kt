package interactor.user

import database.collection.UserCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLoaderImpl @Inject constructor(
    private val userCollection: UserCollection
) : UserLoader {

    override suspend fun validate(name: String, password: String): Boolean = coroutineScope {
        withContext(Dispatchers.IO) {
            userCollection.validate(name, password)
        }
    }
}