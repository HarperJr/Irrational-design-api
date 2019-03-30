package database.document

data class Avatar(
    override val id: String,
    var path: String
) : Document()