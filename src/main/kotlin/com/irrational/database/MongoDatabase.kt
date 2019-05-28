package com.irrational.database

import com.irrational.database.collection.*
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

    override fun arts() = ArtCollection(database.getCollection("art"))

    override fun previewCollection() =
        PreviewCollection(database.getCollection("preview"))

    override fun artists() = ArtistCollection(database.getCollection("artist"))

    override fun roles() = RoleCollection(database.getCollection("role"))

    override fun avatars() = AvatarCollection(database.getCollection("avatar"))

    override fun bookmarks() = BookmarkCollection(database.getCollection("bookmark"))

    override fun likes() = LikeCollection(database.getCollection("like"))

    override fun categories() =
        CategoryCollection(database.getCollection("category"))

    override fun tags() = TagCollection(database.getCollection("tag"))

    override fun comments() = CommentCollection(database.getCollection("insert"))

    override fun followers() = FollowerCollection(database.getCollection("follower"))

    override fun posts() = PostCollection(database.getCollection("post"))

    override fun tagsInPosts() =
        TagInPostCollection(database.getCollection("post_tag"))

    override fun categoriesInPosts() =
        CategoryInPostCollection(database.getCollection("post_category"))

    override fun payments() = PaymentCollection(database.getCollection("payment"))

    override fun virtualWallets() =
        VirtualWalletCollection(database.getCollection("virtual_wallet"))

    override fun walletCards() =
        WalletCardCollection(database.getCollection("wallet_card"))
}