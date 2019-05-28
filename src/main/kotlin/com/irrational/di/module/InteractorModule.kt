package com.irrational.di.module

import dagger.Binds
import dagger.Module
import com.irrational.interactor.art.ArtLoader
import com.irrational.interactor.art.ArtLoaderImpl
import com.irrational.interactor.artist.ArtistLoader
import com.irrational.interactor.artist.ArtistLoaderImpl
import com.irrational.interactor.comment.CommentLoader
import com.irrational.interactor.comment.CommentLoaderImpl
import com.irrational.interactor.payment.PaymentLoader
import com.irrational.interactor.payment.PaymentLoaderImpl
import com.irrational.interactor.post.PostLoader
import com.irrational.interactor.post.PostLoaderImpl

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