package di.module

import dagger.Binds
import dagger.Module
import interactor.comment.CommentInteractor
import interactor.comment.CommentInteractorImpl
import interactor.post.PostInteractor
import interactor.post.PostInteractorImpl

@Module
abstract class InteractorModule {
    @Binds
    internal abstract fun bindPostInteractor(postInteractor: PostInteractorImpl): PostInteractor

    @Binds
    internal abstract fun bindCommentInteractor(commentInteractor: CommentInteractorImpl): CommentInteractor
}