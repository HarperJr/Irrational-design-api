package di.module

import dagger.Module
import dagger.Provides
import database.Database
import database.MongoDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(): Database = MongoDatabase("idesign")
}