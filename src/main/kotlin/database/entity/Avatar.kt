package database.entity

data class Avatar(
    override val id: String,
    var path: String
) : Document()