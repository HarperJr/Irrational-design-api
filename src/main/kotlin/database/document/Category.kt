package database.document

data class Category(
    override val id: String,
    var name: String
) : Document()