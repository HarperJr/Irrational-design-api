package database.document

data class CategoryInPost(
    override val id: String,
    var postId: String,
    var categoryId: String
) : Document()