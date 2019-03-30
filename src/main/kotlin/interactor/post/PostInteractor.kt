package interactor.post

import database.Database
import response.PostResponse

interface PostInteractor {
    fun post(): PostResponse

    companion object : PostInteractor by INSTANCE
}

private val INSTANCE = PostInteractorImpl(
    Database.posts()
)