package database.document

data class Preview(
    override val id: String,
    var name: String,
    var link: String
): Document()