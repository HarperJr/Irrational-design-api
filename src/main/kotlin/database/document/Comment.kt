package database.document

data class Comment(
    override val id: String,
    var artistId: String,
    var postId: String,
    var content: String,
    var date: Long
) : Document()