package database

import database.collection.*

interface Database {

    fun arts(): ArtCollection

    fun artists(): ArtistCollection

    fun avatars(): AvatarCollection

    fun bookmarks(): BookmarkCollection

    fun categories(): CategoryCollection

    fun tags(): TagCollection

    fun comments(): CommentCollection

    fun followers(): FollowerCollection

    fun posts(): PostCollection

    fun tagsInPosts(): TagInPostCollection

    fun categoriesInPosts(): CategoryInPostCollection

    companion object : Database by INSTANCE
}

private val INSTANCE = MongoDatabase("idesign")