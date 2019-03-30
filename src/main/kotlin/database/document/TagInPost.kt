package database.document

data class TagInPost(
    override val id: String,
    var postId: String,
    var tagId: String
) : Document()