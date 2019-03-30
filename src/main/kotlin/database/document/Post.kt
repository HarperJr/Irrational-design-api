package database.document

data class Post(
    override val id: String,
    var artistId: String,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: Long
) : Document()
