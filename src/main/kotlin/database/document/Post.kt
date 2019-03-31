package database.document

import java.util.*

data class Post(
    override val id: String,
    var previewId: String,
    var artistId: String,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: Date
) : Document()
