package com.irrational.request

import com.irrational.database.document.Post
import org.litote.kmongo.Id

data class CommentRequest(
    var postId: Id<Post>,
    var content: String
)