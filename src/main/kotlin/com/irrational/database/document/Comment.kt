package com.irrational.database.document

import org.litote.kmongo.Id

data class Comment(
    var artistId: Id<Artist>,
    var postId: Id<Post>,
    var content: String,
    var date: Long
) : Document<Comment>()