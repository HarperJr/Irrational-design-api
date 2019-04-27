package database.document

import org.litote.kmongo.Id

data class Like(
    var postId: Id<Post>,
    var artistId: Id<Artist>
) : Document<Like>()