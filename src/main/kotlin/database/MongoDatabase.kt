package database

import database.collection.*
import database.entity.*
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

class MongoDatabase(databaseName: String) : Database {

    private val database = KMongo.createClient().getDatabase(databaseName)

    override fun arts() = ArtCollection(database.getCollection<Art>("art"))

    override fun artists() = ArtistCollection(database.getCollection<Artist>("artist"))

    override fun avatars() = AvatarCollection(database.getCollection<Avatar>("avatar"))

    override fun bookmarks() = BookmarkCollection(database.getCollection<Bookmark>("bookmark"))

    override fun categories() = CategoryCollection(database.getCollection<Category>("category"))

    override fun tags() = TagCollection(database.getCollection<Tag>("tag"))

    override fun comments() = CommentCollection(database.getCollection<Comment>("comment"))

    override fun followers() = FollowerCollection(database.getCollection<Follower>("follower"))

    override fun posts() = PostCollection(database.getCollection<Post>("post"))

    override fun tagsInPosts() = TagInPostCollection(database.getCollection<TagInPost>("post_tag"))

    override fun categoriesInPosts() = CategoryInPostCollection(database.getCollection<CategoryInPost>("post_category"))
}