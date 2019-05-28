package com.irrational.database.document

import org.litote.kmongo.Id

data class Preview(
    var postId: Id<Post>,
    var link: String
) : Document<Preview>()