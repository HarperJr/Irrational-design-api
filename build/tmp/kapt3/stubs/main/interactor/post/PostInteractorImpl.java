package interactor.post;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Linteractor/post/PostInteractorImpl;", "Linteractor/post/PostInteractor;", "postCollection", "Ldatabase/collection/PostCollection;", "artistCollection", "Ldatabase/collection/ArtistCollection;", "artCollection", "Ldatabase/collection/ArtCollection;", "tagCollection", "Ldatabase/collection/TagCollection;", "categoryCollection", "Ldatabase/collection/CategoryCollection;", "tagInPostCollection", "Ldatabase/collection/TagInPostCollection;", "categoryInPostCollection", "Ldatabase/collection/CategoryInPostCollection;", "(Ldatabase/collection/PostCollection;Ldatabase/collection/ArtistCollection;Ldatabase/collection/ArtCollection;Ldatabase/collection/TagCollection;Ldatabase/collection/CategoryCollection;Ldatabase/collection/TagInPostCollection;Ldatabase/collection/CategoryInPostCollection;)V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "post", "Lresponse/PostResponse;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "id-server"})
public final class PostInteractorImpl implements interactor.post.PostInteractor {
    private final java.text.SimpleDateFormat dateFormatter = null;
    private final database.collection.PostCollection postCollection = null;
    private final database.collection.ArtistCollection artistCollection = null;
    private final database.collection.ArtCollection artCollection = null;
    private final database.collection.TagCollection tagCollection = null;
    private final database.collection.CategoryCollection categoryCollection = null;
    private final database.collection.TagInPostCollection tagInPostCollection = null;
    private final database.collection.CategoryInPostCollection categoryInPostCollection = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object post(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super response.PostResponse> p1) {
        return null;
    }
    
    @javax.inject.Inject()
    public PostInteractorImpl(@org.jetbrains.annotations.NotNull()
    database.collection.PostCollection postCollection, @org.jetbrains.annotations.NotNull()
    database.collection.ArtistCollection artistCollection, @org.jetbrains.annotations.NotNull()
    database.collection.ArtCollection artCollection, @org.jetbrains.annotations.NotNull()
    database.collection.TagCollection tagCollection, @org.jetbrains.annotations.NotNull()
    database.collection.CategoryCollection categoryCollection, @org.jetbrains.annotations.NotNull()
    database.collection.TagInPostCollection tagInPostCollection, @org.jetbrains.annotations.NotNull()
    database.collection.CategoryInPostCollection categoryInPostCollection) {
        super();
    }
}