package response

import database.document.Post
import org.litote.kmongo.Id

data class PostResponse(
    var id: Id<Post>,
    var artist: ArtistResponse,
    var arts: List<ArtResponse>,
    var title: String,
    var subtitle: String,
    var description: String,
    var likes: Long,
    var bookmarks: Long,
    var tags: List<TagResponse>,
    var categories: List<CategoryResponse>,
    var date: Long
)