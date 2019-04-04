package database.document

data class Art(
    var postId: String,
    var name: String,
    var link: String
) : Document<Art>()