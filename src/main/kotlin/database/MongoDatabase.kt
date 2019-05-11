package database

import database.collection.*
import database.document.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class MongoDatabase(databaseName: String) : Database {

    private val mongoClient = KMongo.createClient()
    private val database = mongoClient.coroutine.getDatabase(databaseName)

    override suspend fun runInTx(action: suspend CoroutineScope.() -> Unit) {
        coroutineScope {
            val session = mongoClient.startSession().awaitSingle()
            with(session) {
                startTransaction()
                action.invoke(this@coroutineScope)
                commitTransaction()
            }
        }
    }

    override suspend fun <T> callInTx(call: suspend CoroutineScope.() -> T): T {
        return coroutineScope {
            val session = mongoClient.startSession().awaitSingle()
            with(session) {
                startTransaction()
                val result = call.invoke(this@coroutineScope)
                commitTransaction()
                result
            }
        }
    }

    override fun arts() = ArtCollection(database.getCollection<Art>("art"))

    override fun previewCollection() = PreviewCollection(database.getCollection<Preview>("preview"))

    override fun artists() = ArtistCollection(database.getCollection<Artist>("artist"))

    override fun avatars() = AvatarCollection(database.getCollection<Avatar>("avatar"))

    override fun bookmarks() = BookmarkCollection(database.getCollection<Bookmark>("bookmark"))

    override fun likes() = LikeCollection(database.getCollection<Like>("like"))

    override fun categories() = CategoryCollection(database.getCollection<Category>("category"))

    override fun tags() = TagCollection(database.getCollection<Tag>("tag"))

    override fun comments() = CommentCollection(database.getCollection<Comment>("comment"))

    override fun followers() = FollowerCollection(database.getCollection<Follower>("follower"))

    override fun posts() = PostCollection(database.getCollection<Post>("post"))

    override fun tagsInPosts() = TagInPostCollection(database.getCollection<TagInPost>("post_tag"))

    override fun categoriesInPosts() = CategoryInPostCollection(database.getCollection<CategoryInPost>("post_category"))

    override fun payments() = PaymentCollection(database.getCollection<Payment>("payment"))
}