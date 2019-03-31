package di.module

import dagger.Module
import dagger.Provides
import database.Database
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
    fun provideAvatarCollection(database: Database) = database.avatars()

    @Provides
    @Singleton
    fun provideBookmarkCollection(database: Database) = database.bookmarks()

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
}