package database.document

data class Artist(
    override val id: String,
    var name: String,
    var permalink: String,
    var email: String,
    var avatarId: String
) : Document()