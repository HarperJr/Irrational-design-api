package di

import dagger.Component
import di.module.CollectionsModule
import di.module.DatabaseModule
import di.module.InteractorModule
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

    companion object : AppComponent by INSTANCE
}

private val INSTANCE = DaggerAppComponent.create()