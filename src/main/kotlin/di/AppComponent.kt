package di

import dagger.Component
import di.module.CollectionsModule
import di.module.DatabaseModule
import di.module.InteractorModule
import interactor.post.PostInteractor
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
    fun postInteractor(): PostInteractor
}