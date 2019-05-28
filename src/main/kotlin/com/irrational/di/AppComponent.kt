package com.irrational.di

import dagger.Component
import com.irrational.di.module.CollectionsModule
import com.irrational.di.module.DatabaseModule
import com.irrational.di.module.InteractorModule
import com.irrational.interactor.art.ArtLoader
import com.irrational.interactor.artist.ArtistLoader
import com.irrational.interactor.comment.CommentLoader
import com.irrational.interactor.payment.PaymentLoader
import com.irrational.interactor.post.PostLoader
import di.DaggerAppComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        CollectionsModule::class,
        InteractorModule::class
    ]
)
interface AppComponent {
    fun postLoader(): PostLoader
    fun commentLoader(): CommentLoader
    fun artistLoader(): ArtistLoader
    fun artLoader(): ArtLoader
    fun paymentLoader(): PaymentLoader

    companion object : AppComponent by INSTANCE
}

private val INSTANCE = DaggerAppComponent.create()