package database.document

import org.litote.kmongo.Id
import java.util.*

data class Post(
    var previewId: Id<Preview>,
    var artistId: Id<Artist>,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: Long
) : Document<Post>()
