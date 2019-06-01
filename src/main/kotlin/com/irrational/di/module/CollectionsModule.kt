package com.irrational.di.module

import dagger.Module
import dagger.Provides
import com.irrational.database.Database
import javax.inject.Singleton

@Module
class CollectionsModule {

    @Provides
    @Singleton
    fun provideArtCollection(database: Database) = database.arts()

    @Provides
    @Singleton
    fun providePreviewCollection(database: Database) = database.previewCollection()

    @Provides
    @Singleton
    fun provideArtistCollection(database: Database) = database.artists()

    @Provides
    @Singleton
    fun provideRoleCollection(database: Database) = database.roles()

    @Provides
    @Singleton
    fun provideAvatarCollection(database: Database) = database.avatars()

    @Provides
    @Singleton
    fun provideBookmarkCollection(database: Database) = database.bookmarks()


    @Provides
    @Singleton
    fun provideLikeCollection(database: Database) = database.likes()

    @Provides
    @Singleton
    fun provideCategoryCollection(database: Database) = database.categories()

    @Provides
    @Singleton
    fun provideTagCollection(database: Database) = database.tags()

    @Provides
    @Singleton
    fun provideCommentCollection(database: Database) = database.comments()

    @Provides
    @Singleton
    fun provideFollowerCollection(database: Database) = database.followers()

    @Provides
    @Singleton
    fun providePostCollection(database: Database) = database.posts()

    @Provides
    @Singleton
    fun provideTagInPosCollection(database: Database) = database.tagsInPosts()

    @Provides
    @Singleton
    fun provideCategoryInPostCollection(database: Database) = database.categoriesInPosts()

    @Provides
    @Singleton
    fun providePaymentCollection(database: Database) = database.payments()

    @Provides
    @Singleton
    fun provideVirtualWalletCollection(database: Database) = database.virtualWallets()

    @Provides
    @Singleton
    fun provideWalletCardCollection(database: Database) = database.walletCards()
}