package response

import database.document.Artist
import org.litote.kmongo.Id

data class ArtistResponse(
    var id: Id<Artist>,
    var name: String,
    var followed: Boolean,
    var permalink: String,
    var email: String,
    var avatar: AvatarResponse
)