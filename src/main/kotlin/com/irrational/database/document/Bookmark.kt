package com.irrational.database.document

import org.litote.kmongo.Id

data class Bookmark(
    var postId: Id<Post>,
    var artistId: Id<Artist>
) : Document<Bookmark>()