package database.document

import org.litote.kmongo.Id

data class Post(
    var artistId: Id<Artist>,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: Long
) : Document<Post>()
