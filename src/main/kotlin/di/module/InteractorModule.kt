package di.module

import dagger.Binds
import dagger.Module
import interactor.artist.ArtistLoader
import interactor.artist.ArtistLoaderImpl
import interactor.comment.CommentLoader
import interactor.comment.CommentLoaderImpl
import interactor.post.PostLoader
import interactor.post.PostLoaderImpl
import interactor.user.UserLoader
import interactor.user.UserLoaderImpl

@Module
abstract class InteractorModule {
    @Binds
    internal abstract fun bindPostInteractor(postLoader: PostLoaderImpl): PostLoader

    @Binds
    internal abstract fun bindCommentInteractor(commentLoader: CommentLoaderImpl): CommentLoader

    @Binds
    internal abstract fun bindArtistInteractor(artistLoader: ArtistLoaderImpl): ArtistLoader

    @Binds
    internal abstract fun bindUserInteractor(userLoader: UserLoaderImpl): UserLoader
}