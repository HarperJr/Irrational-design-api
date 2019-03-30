package response

data class ArtistResponse(
    var id: String,
    var name: String,
    var followed: Boolean,
    var permalink: String,
    var email: String,
    var avatar: AvatarResponse
) {
    class AvatarResponse(var link: String)
}