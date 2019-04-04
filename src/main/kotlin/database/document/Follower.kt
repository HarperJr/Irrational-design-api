package database.document

import org.litote.kmongo.Id

data class Follower(
    var artistId: Id<Artist>,
    var followerId: Id<Artist>
) : Document<Follower>()