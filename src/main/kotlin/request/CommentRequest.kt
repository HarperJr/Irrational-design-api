package request

import database.document.Artist
import database.document.Post
import org.litote.kmongo.Id

data class CommentRequest(
    var postId: Id<Post>,
    var author: Id<Artist>,
    var content: String
)