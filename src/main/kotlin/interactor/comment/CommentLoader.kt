package interactor.comment

import database.document.Post
import di.AppComponent
import org.litote.kmongo.Id
import response.CommentResponse

interface CommentLoader {
    suspend fun comments(id: String): List<CommentResponse>

    suspend fun insert(postId: Id<Post>, author: String, content: String)

    companion object : CommentLoader by INSTANCE
}

private val INSTANCE = AppComponent.commentLoader()