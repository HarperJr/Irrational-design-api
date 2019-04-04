package database.document

import org.litote.kmongo.Id

data class Artist(
    var name: String,
    var permalink: String,
    var email: String,
    var avatarId: Id<Avatar>
) : Document<Artist>()