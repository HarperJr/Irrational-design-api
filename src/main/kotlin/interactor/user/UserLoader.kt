package interactor.user

import di.AppComponent

interface UserLoader {
    suspend fun validate(name: String, password: String): Boolean

    companion object : UserLoader by INSTANCE
}

private val INSTANCE = AppComponent.userLoader()