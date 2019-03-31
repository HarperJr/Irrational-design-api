package di.module

import dagger.Binds
import dagger.Module
import interactor.comment.CommentLoader
import interactor.comment.CommentLoaderImpl
import interactor.post.PostLoader
import interactor.post.PostLoaderImpl

@Module
abstract class InteractorModule {
    @Binds
    internal abstract fun bindPostInteractor(postLoader: PostLoaderImpl): PostLoader

    @Binds
    internal abstract fun bindCommentInteractor(commentLoader: CommentLoaderImpl): CommentLoader
}