package database.entity

data class Artist(
    override val id: String,
    var name: String
) : Document()