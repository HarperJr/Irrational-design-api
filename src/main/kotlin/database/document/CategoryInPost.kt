package database.document

import org.litote.kmongo.Id

data class CategoryInPost(
    var postId: Id<Post>,
    var categoryId: Id<Category>
) : Document<CategoryInPost>()