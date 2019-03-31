package di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!\u00a2\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH!\u00a2\u0006\u0002\b\f\u00a8\u0006\r"}, d2 = {"Ldi/module/InteractorModule;", "", "()V", "bindCommentInteractor", "Linteractor/comment/CommentInteractor;", "commentInteractor", "Linteractor/comment/CommentInteractorImpl;", "bindCommentInteractor$id_server", "bindPostInteractor", "Linteractor/post/PostInteractor;", "postInteractor", "Linteractor/post/PostInteractorImpl;", "bindPostInteractor$id_server", "id-server"})
@dagger.Module()
public abstract class InteractorModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract interactor.post.PostInteractor bindPostInteractor$id_server(@org.jetbrains.annotations.NotNull()
    interactor.post.PostInteractorImpl postInteractor);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract interactor.comment.CommentInteractor bindCommentInteractor$id_server(@org.jetbrains.annotations.NotNull()
    interactor.comment.CommentInteractorImpl commentInteractor);
    
    public InteractorModule() {
        super();
    }
}