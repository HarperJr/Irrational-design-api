package di.module

import dagger.Module
import dagger.Provides
import database.Database
import database.MongoDatabase
import database.transaction.MongoTransaction
import database.transaction.Transaction
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(): Database = MongoDatabase("idesign")

    @Provides
    @Singleton
    fun provideTransaction(database: Database): Transaction = MongoTransaction(database)
}