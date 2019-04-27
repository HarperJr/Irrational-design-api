package response

import database.document.Bookmark
import database.document.Post
import org.litote.kmongo.Id

data class FeedPostResponse(
    var id: Id<Post>,
    var preview: ArtResponse,
    var artist: ArtistResponse,
    var title: String,
    var subtitle: String,
    // var likes: Int,
    var bookmarks: List<Bookmark>,
    var comments: List<CommentResponse>,
    var date: Long
)