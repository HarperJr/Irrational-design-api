package com.irrational.interactor.post

import com.irrational.ImageFile
import com.irrational.database.document.Post
import com.irrational.di.AppComponent
import com.irrational.response.FeedPostResponse
import com.irrational.response.LikedResponse
import com.irrational.response.PostResponse

interface PostLoader {

    suspend fun upload(
        post: Post, categories: List<String>,
        tags: List<String>, imageFiles: List<ImageFile>
    )

    suspend fun post(artistId: String?, id: String): PostResponse

    suspend fun posts(from: Int, to: Int, filter: String): List<FeedPostResponse>

    suspend fun like(id: String, artistId: String, initial: Boolean)

    suspend fun bookmark(id: String, artistId: String, initial: Boolean)

    suspend fun categories(): List<String>

    suspend fun liked(artistId: String, id: String): LikedResponse

    companion object : PostLoader by INSTANCE
}

private val INSTANCE = AppComponent.postLoader()
