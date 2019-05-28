package com.irrational.response

import com.irrational.database.document.Post
import org.litote.kmongo.Id

data class FeedPostResponse(
    var id: Id<Post>,
    var preview: String?,
    var artist: ArtistResponse,
    var title: String,
    var subtitle: String,
    var description: String,
    var likes: Long,
    var bookmarks: Long,
    var comments: Long,
    var date: Long,
    var categories: List<CategoryResponse>,
    var tags: List<TagResponse>
)