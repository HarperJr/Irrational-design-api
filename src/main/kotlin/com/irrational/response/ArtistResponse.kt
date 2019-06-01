package com.irrational.response

import com.irrational.database.document.Artist
import org.litote.kmongo.Id

data class ArtistResponse(
    var id: Id<Artist>,
    var name: String,
    var followed: Boolean,
    var followers: Int,
    var follows: Int,
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