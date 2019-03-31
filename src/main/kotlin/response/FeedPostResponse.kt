package response

import java.util.*

data class FeedPostResponse(
    var id: String,
    var preview: ArtResponse,
    var artist: ArtistResponse,
    var title: String,
    var subtitle: String,
    var likes: Int,
    var bookmarks: Int,
    var comments: Int,
    var date: Date
)