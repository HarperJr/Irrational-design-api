package com.irrational.database

import com.irrational.database.collection.*
import kotlinx.coroutines.CoroutineScope

interface Database {

    suspend fun runInTx(action: suspend CoroutineScope.() -> Unit)

    suspend fun <T> callInTx(call: suspend CoroutineScope.() -> T): T

    fun arts(): ArtCollection

    fun previewCollection(): PreviewCollection

    fun artists(): ArtistCollection

    fun roles(): RoleCollection

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

    fun payments(): PaymentCollection

    fun virtualWallets(): VirtualWalletCollection

    fun walletCards(): WalletCardCollection
}
