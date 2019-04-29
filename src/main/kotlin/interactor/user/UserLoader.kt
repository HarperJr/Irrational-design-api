package interactor.user

import database.document.User
import di.AppComponent

interface UserLoader {
    suspend fun identify(name: String, password: String): User?

    suspend fun find(id: String): User?

    companion object : UserLoader by INSTANCE
}

private val INSTANCE = AppComponent.userLoader()