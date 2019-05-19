package interactor.artist

import database.document.Artist
import di.AppComponent
import response.ArtistResponse
import response.FollowedResponse
import response.FollowersResponse
import response.FollowsResponse

interface ArtistLoader {
    suspend fun artist(id: String): ArtistResponse

    suspend fun findByCredentials(name: String, password: String): Artist?

    suspend fun find(id: String): Artist?

    suspend fun insert(name: String, password: String, email: String)

    suspend fun follow(followerId: String, artistId: String, initial: Boolean)

    suspend fun followers(artistId: String): FollowersResponse

    suspend fun follows(artistId: String): FollowsResponse

    suspend fun followed(artistId: String, id: String): FollowedResponse

    companion object : ArtistLoader by INSTANCE
}

private val INSTANCE = AppComponent.artistLoader()