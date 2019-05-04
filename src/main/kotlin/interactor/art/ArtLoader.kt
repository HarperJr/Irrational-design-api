package interactor.art

import di.AppComponent

interface ArtLoader {
    suspend fun save(bytes: ByteArray)

    companion object : ArtLoader by INSTANCE
}

private val INSTANCE = AppComponent.artLoader()