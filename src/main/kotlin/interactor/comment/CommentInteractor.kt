package interactor.comment

import response.CommentResponse

interface CommentInteractor {
    suspend fun comments(id: String): List<CommentResponse>
}