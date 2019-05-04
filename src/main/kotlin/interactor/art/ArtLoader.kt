package interactor.art

import database.document.Art
import di.AppComponent

interface ArtLoader {
    suspend fun insert(arts: List<Art>)

    companion object : ArtLoader by INSTANCE
}

private val INSTANCE = AppComponent.artLoader()