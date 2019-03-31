package database.document

data class Avatar(
    override val id: String,
    var link: String
) : Document()