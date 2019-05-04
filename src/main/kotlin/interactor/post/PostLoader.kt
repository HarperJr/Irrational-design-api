package interactor.post

import di.AppComponent
import response.FeedPostResponse
import response.PostResponse

interface PostLoader {
    suspend fun post(id: String): PostResponse?

    suspend fun posts(from: Int, to: Int, filter: String): List<FeedPostResponse>

    suspend fun insert(
        artistId: String, title: String, subtitle: String, description: String,
        categories: List<String>, tags: List<String>
    )

    companion object : PostLoader by INSTANCE
}

private val INSTANCE = AppComponent.postLoader()
