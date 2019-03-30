package database.document

data class Tag(
    override val id: String,
    var name: String
) : Document()