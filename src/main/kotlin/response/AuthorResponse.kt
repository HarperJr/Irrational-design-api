package response

import database.document.Artist
import org.litote.kmongo.Id

data class AuthorResponse(
    var authorId: Id<Artist>,
    var name: String,
    var avatar: AvatarResponse?
)