package database.document

import org.litote.kmongo.Id
import java.util.*

data class Artist(
    var name: String,
    var password: String,
    var email: String,
    var registered: Long = Date().time,
    var avatarId: Id<Avatar>? = null
) : Document<Artist>()