package di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Ldi/AppComponent;", "", "commentInteractor", "Linteractor/comment/CommentInteractor;", "postInteractor", "Linteractor/post/PostInteractor;", "id-server"})
@dagger.Component(modules = {di.module.DatabaseModule.class, di.module.CollectionsModule.class, di.module.InteractorModule.class})
@javax.inject.Singleton()
public abstract interface AppComponent {
    
    @org.jetbrains.annotations.NotNull()
    public abstract interactor.post.PostInteractor postInteractor();
    
    @org.jetbrains.annotations.NotNull()
    public abstract interactor.comment.CommentInteractor commentInteractor();
}