package di.module

import dagger.Binds
import dagger.Module
import interactor.post.PostInteractor
import interactor.post.PostInteractorImpl

@Module
abstract class InteractorModule {
    @Binds
    internal abstract fun bindPostInteractor(postInteractor: PostInteractorImpl): PostInteractor
}