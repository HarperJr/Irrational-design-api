package interactor.comment

import database.document.Artist
import database.document.Post
import di.AppComponent
import org.litote.kmongo.Id
import response.CommentResponse

interface CommentLoader {
    suspend fun comments(id: String): List<CommentResponse>
    suspend fun comment(postId: Id<Post>, author: Id<Artist>, content: String)

    companion object : CommentLoader by INSTANCE
}

private val INSTANCE = AppComponent.commentLoader()