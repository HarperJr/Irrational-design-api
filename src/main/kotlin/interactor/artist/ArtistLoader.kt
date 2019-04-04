package interactor.artist

import di.AppComponent
import response.ArtistResponse

interface ArtistLoader {
    suspend fun artist(id: String): ArtistResponse?

    companion object : ArtistLoader by INSTANCE
}

private val INSTANCE = AppComponent.artistLoader()