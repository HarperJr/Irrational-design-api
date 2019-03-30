package database.entity

data class Art(
    override val id: String,
    var name: String,
    var path: String
) : Document()