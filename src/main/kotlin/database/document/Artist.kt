package database.document

import org.litote.kmongo.Id

data class Artist(
    var name: String,
    var password: String,
    var email: String,
    var registered: Long,
    var avatarId: Id<Avatar>? = null
) : Document<Artist>()