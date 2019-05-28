package com.irrational.database.document

import org.litote.kmongo.Id

data class TagInPost(
    var postId: Id<Post>,
    var tagId: Id<Tag>
) : Document<TagInPost>()