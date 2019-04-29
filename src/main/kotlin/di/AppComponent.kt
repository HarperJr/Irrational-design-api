package di

import dagger.Component
import di.module.CollectionsModule
import di.module.DatabaseModule
import di.module.InteractorModule
import interactor.artist.ArtistLoader
import interactor.comment.CommentLoader
import interactor.post.PostLoader
import interactor.user.UserLoader
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
    fun userLoader(): UserLoader

    companion object : AppComponent by INSTANCE
}

private val INSTANCE = DaggerAppComponent.create()