package interactor.comment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Linteractor/comment/CommentInteractorImpl;", "Linteractor/comment/CommentInteractor;", "commentCollection", "Ldatabase/collection/CommentCollection;", "artistCollection", "Ldatabase/collection/ArtistCollection;", "avatarCollection", "Ldatabase/collection/AvatarCollection;", "(Ldatabase/collection/CommentCollection;Ldatabase/collection/ArtistCollection;Ldatabase/collection/AvatarCollection;)V", "comments", "", "Lresponse/CommentResponse;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "id-server"})
public final class CommentInteractorImpl implements interactor.comment.CommentInteractor {
    private final database.collection.CommentCollection commentCollection = null;
    private final database.collection.ArtistCollection artistCollection = null;
    private final database.collection.AvatarCollection avatarCollection = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object comments(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<response.CommentResponse>> p1) {
        return null;
    }
    
    @javax.inject.Inject()
    public CommentInteractorImpl(@org.jetbrains.annotations.NotNull()
    database.collection.CommentCollection commentCollection, @org.jetbrains.annotations.NotNull()
    database.collection.ArtistCollection artistCollection, @org.jetbrains.annotations.NotNull()
    database.collection.AvatarCollection avatarCollection) {
        super();
    }
}