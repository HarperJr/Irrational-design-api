package interactor.post

import di.AppComponent
import request.PostRequest
import response.FeedPostResponse
import response.PostResponse

interface PostLoader {
    suspend fun post(id: String): PostResponse?

    suspend fun posts(from: Int, to: Int): List<FeedPostResponse>

    suspend fun upload(fields: PostRequest)

    companion object : PostLoader by INSTANCE
}

private val INSTANCE = AppComponent.postLoader()
