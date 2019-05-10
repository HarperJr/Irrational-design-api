package request

data class CommentRequest(
    var postId: String,
    var content: String
)