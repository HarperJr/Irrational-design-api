package com.irrational.di.module

import dagger.Module
import dagger.Provides
import com.irrational.database.Database
import com.irrational.database.MongoDatabase
import com.irrational.database.transaction.MongoTransaction
import com.irrational.database.transaction.Transaction
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(): Database = MongoDatabase("idesign")

    @Provides
    @Singleton
    fun provideTransaction(database: Database): Transaction =
        MongoTransaction(database)
}