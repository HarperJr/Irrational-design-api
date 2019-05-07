package di

import dagger.Component
import di.module.ApiModule
import di.module.CollectionsModule
import di.module.DatabaseModule
import di.module.InteractorModule
import interactor.art.ArtLoader
import interactor.artist.ArtistLoader
import interactor.comment.CommentLoader
import interactor.post.PostLoader
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

    fun apiComponent(): ApiComponent

    companion object : AppComponent by INSTANCE
}

private val INSTANCE = DaggerAppComponent.create()