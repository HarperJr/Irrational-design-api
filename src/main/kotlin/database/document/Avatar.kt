package database.document

import org.litote.kmongo.Id

data class Avatar(
    var artistId: Id<Artist>,
    var link: String
) : Document<Avatar>()