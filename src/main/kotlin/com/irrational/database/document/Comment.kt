package com.irrational.database.document

import org.litote.kmongo.Id
import java.util.*

data class Comment(
    var artistId: Id<Artist>,
    var postId: Id<Post>,
    var content: String,
    var date: Long = Date().time
) : Document<Comment>()