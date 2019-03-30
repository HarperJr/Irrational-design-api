package interactor.post

import database.collection.PostCollection
import response.PostResponse

class PostInteractorImpl constructor(
    private val postCollection: PostCollection
) : PostInteractor {

    override fun post(): PostResponse {
        return PostResponse("")
    }
}