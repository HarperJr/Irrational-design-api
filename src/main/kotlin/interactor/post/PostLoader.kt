package interactor.post

import database.document.Post
import di.AppComponent
import response.FeedPostResponse
import response.PostResponse

interface PostLoader {
    suspend fun insert(post: Post, categories: List<String>, tags: List<String>): String

    suspend fun post(artistId: String?, id: String): PostResponse?

    suspend fun posts(from: Int, to: Int, filter: String): List<FeedPostResponse>

    suspend fun like(id: String, artistId: String, initial: Boolean)

    suspend fun bookmark(id: String, artistId: String, initial: Boolean)

    companion object : PostLoader by INSTANCE
}

private val INSTANCE = AppComponent.postLoader()
