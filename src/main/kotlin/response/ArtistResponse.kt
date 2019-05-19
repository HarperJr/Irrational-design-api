package response

import database.document.Artist
import org.litote.kmongo.Id

data class ArtistResponse(
    var id: Id<Artist>,
    var name: String,
    var followed: Boolean,
    var email: String,
    var avatar: AvatarResponse?
)

data class FollowersResponse(
    var followers: List<ArtistResponse>
)

data class FollowsResponse(
    var follows: List<ArtistResponse>
)

data class FollowedResponse(
    var followed: Boolean
)