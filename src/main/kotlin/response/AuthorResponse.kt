package response

data class AuthorResponse (
    var name: String,
    var permalink: String,
    var avatar: AvatarResponse
)