package database

import database.collection.*

interface Database {

    fun arts(): ArtCollection

    fun previewCollection(): PreviewCollection

    fun artists(): ArtistCollection

    fun avatars(): AvatarCollection

    fun bookmarks(): BookmarkCollection

    fun likes(): LikeCollection

    fun categories(): CategoryCollection

    fun tags(): TagCollection

    fun comments(): CommentCollection

    fun followers(): FollowerCollection

    fun posts(): PostCollection

    fun tagsInPosts(): TagInPostCollection

    fun categoriesInPosts(): CategoryInPostCollection
}
