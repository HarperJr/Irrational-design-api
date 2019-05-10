package interactor.artist

import database.document.Artist
import di.AppComponent
import response.ArtistResponse

interface ArtistLoader {
    suspend fun artist(id: String): ArtistResponse?

    suspend fun findByCredentials(name: String, password: String): Artist?

    suspend fun find(id: String): Artist?

    suspend fun insert(name: String, password: String, email: String)

    suspend fun follow(followerId: String, artistId: String)

    suspend fun unfollow(followerId: String, artistId: String)

    companion object : ArtistLoader by INSTANCE
}

private val INSTANCE = AppComponent.artistLoader()