package database.document

data class Art(
    override val id: String,
    var postId: String,
    var name: String,
    var link: String
) : Document()