package database.document

import org.litote.kmongo.Id

data class Art(
    var postId: Id<Post>,
    var link: String
) : Document<Art>()