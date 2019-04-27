package request

data class CommentRequest(
    var postId: String,
    var author: String,
    var content: String
)