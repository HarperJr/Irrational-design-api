package interactor.post

import response.PostResponse

interface PostInteractor {
    suspend fun post(id: String): PostResponse?
}
