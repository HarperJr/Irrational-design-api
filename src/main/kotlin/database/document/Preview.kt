package database.document

data class Preview(
    var name: String,
    var link: String
): Document<Preview>()