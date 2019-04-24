package response

import database.document.Post
import org.litote.kmongo.Id
import java.util.*

data class PostResponse(
    var id: Id<Post>,
    var artist: ArtistResponse,
    var arts: List<ArtResponse>,
    var title: String,
    var subtitle: String,
    var description: String,
    var likes: Int,
    var bookmarks: Int,
    var comments: Int,
    var tags: List<TagResponse>,
    var categories: List<CategoryResponse>,
    var date: Long
)