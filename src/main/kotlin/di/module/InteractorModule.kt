package di.module

import dagger.Binds
import dagger.Module
import interactor.art.ArtLoader
import interactor.art.ArtLoaderImpl
import interactor.artist.ArtistLoader
import interactor.artist.ArtistLoaderImpl
import interactor.comment.CommentLoader
import interactor.comment.CommentLoaderImpl
import interactor.payment.PaymentLoader
import interactor.payment.PaymentLoaderImpl
import interactor.post.PostLoader
import interactor.post.PostLoaderImpl

@Module
abstract class InteractorModule {
    @Binds
    internal abstract fun bindPostInteractor(postLoader: PostLoaderImpl): PostLoader

    @Binds
    internal abstract fun bindCommentInteractor(commentLoader: CommentLoaderImpl): CommentLoader

    @Binds
    internal abstract fun bindArtistInteractor(artistLoader: ArtistLoaderImpl): ArtistLoader

    @Binds
    internal abstract fun bindArtInteractor(artLoader: ArtLoaderImpl): ArtLoader

    @Binds
    internal abstract fun bindPaymentInteractor(paymentLoader: PaymentLoaderImpl): PaymentLoader
}