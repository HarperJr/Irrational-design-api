package interactor.comment;

import dagger.internal.Factory;
import database.collection.ArtistCollection;
import database.collection.AvatarCollection;
import database.collection.CommentCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CommentInteractorImpl_Factory implements Factory<CommentInteractorImpl> {
  private final Provider<CommentCollection> commentCollectionProvider;

  private final Provider<ArtistCollection> artistCollectionProvider;

  private final Provider<AvatarCollection> avatarCollectionProvider;

  public CommentInteractorImpl_Factory(
      Provider<CommentCollection> commentCollectionProvider,
      Provider<ArtistCollection> artistCollectionProvider,
      Provider<AvatarCollection> avatarCollectionProvider) {
    this.commentCollectionProvider = commentCollectionProvider;
    this.artistCollectionProvider = artistCollectionProvider;
    this.avatarCollectionProvider = avatarCollectionProvider;
  }

  @Override
  public CommentInteractorImpl get() {
    return new CommentInteractorImpl(
        commentCollectionProvider.get(),
        artistCollectionProvider.get(),
        avatarCollectionProvider.get());
  }

  public static CommentInteractorImpl_Factory create(
      Provider<CommentCollection> commentCollectionProvider,
      Provider<ArtistCollection> artistCollectionProvider,
      Provider<AvatarCollection> avatarCollectionProvider) {
    return new CommentInteractorImpl_Factory(
        commentCollectionProvider, artistCollectionProvider, avatarCollectionProvider);
  }

  public static CommentInteractorImpl newCommentInteractorImpl(
      CommentCollection commentCollection,
      ArtistCollection artistCollection,
      AvatarCollection avatarCollection) {
    return new CommentInteractorImpl(commentCollection, artistCollection, avatarCollection);
  }
}
