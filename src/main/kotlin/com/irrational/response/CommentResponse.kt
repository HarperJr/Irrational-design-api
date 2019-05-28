package com.irrational.response

data class CommentResponse(
    var author: AuthorResponse,
    var content: String,
    var date: Long

)