package interactor.comment

import di.AppComponent
import response.CommentResponse

interface CommentLoader {
    suspend fun comments(id: String): List<CommentResponse>
    suspend fun comment(postId: String, author: String, content: String)

    companion object : CommentLoader by INSTANCE
}

private val INSTANCE = AppComponent.commentLoader()