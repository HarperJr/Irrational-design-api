package com.irrational.interactor.post

import com.irrational.ImageFile
import com.irrational.database.document.Artist
import com.irrational.database.document.Post
import com.irrational.di.AppComponent
import com.irrational.response.FeedPostResponse
import com.irrational.response.LikedResponse
import com.irrational.response.PostResponse
import com.irrational.response.StatusResponse
import org.litote.kmongo.Id

interface PostLoader {

    suspend fun upload(
        post: Post, categories: List<String>,
        tags: List<String>, imageFiles: List<ImageFile>
    )

    suspend fun post(artistId: String?, id: String): PostResponse

    suspend fun posts(from: Int, to: Int, filter: String): List<FeedPostResponse>

    suspend fun like(id: String, artistId: String, initial: Boolean): LikedResponse

    suspend fun bookmark(id: String, artistId: String, initial: Boolean)

    suspend fun categories(): List<String>

    suspend fun liked(artistId: String, id: String): LikedResponse

    suspend fun delete(postId: Id<Post>, artistId: Id<Artist>): StatusResponse

    companion object : PostLoader by INSTANCE
}

private val INSTANCE = AppComponent.postLoader()
