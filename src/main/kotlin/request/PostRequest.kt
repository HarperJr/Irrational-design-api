package request

import database.document.*
import org.litote.kmongo.Id
import java.beans.BeanDescriptor

data class PostRequest (
    var artist: Id<Artist>,
    var preview : Id<Preview>,
    var title: String,
    var subtitle: String,
    var description: String,
    var categories: List<Id<Category>>,
    var tags: List<Id<Tag>>
)