package database.document

data class Avatar(
    // var artistId: Id<Artist>,
    var link: String
) : Document<Avatar>()