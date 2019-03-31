package response

import java.util.*

data class CommentResponse (
        var author: AuthorResponse,
        var content: String,
        var date: Date

)