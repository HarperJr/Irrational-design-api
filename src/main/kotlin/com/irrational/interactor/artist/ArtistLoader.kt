package com.irrational.interactor.artist

import com.irrational.database.document.Artist
import com.irrational.di.AppComponent
import com.irrational.response.ArtistResponse
import com.irrational.response.FollowedResponse
import com.irrational.response.FollowersResponse
import com.irrational.response.FollowsResponse

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