package interactor.post;

import dagger.internal.Factory;
import database.collection.ArtCollection;
import database.collection.ArtistCollection;
import database.collection.CategoryCollection;
import database.collection.CategoryInPostCollection;
import database.collection.PostCollection;
import database.collection.TagCollection;
import database.collection.TagInPostCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class PostInteractorImpl_Factory implements Factory<PostInteractorImpl> {
  private final Provider<PostCollection> postCollectionProvider;

  private final Provider<ArtistCollection> artistCollectionProvider;

  private final Provider<ArtCollection> artCollectionProvider;

  private final Provider<TagCollection> tagCollectionProvider;

  private final Provider<CategoryCollection> categoryCollectionProvider;

  private final Provider<TagInPostCollection> tagInPostCollectionProvider;

  private final Provider<CategoryInPostCollection> categoryInPostCollectionProvider;

  public PostInteractorImpl_Factory(
      Provider<PostCollection> postCollectionProvider,
      Provider<ArtistCollection> artistCollectionProvider,
      Provider<ArtCollection> artCollectionProvider,
      Provider<TagCollection> tagCollectionProvider,
      Provider<CategoryCollection> categoryCollectionProvider,
      Provider<TagInPostCollection> tagInPostCollectionProvider,
      Provider<CategoryInPostCollection> categoryInPostCollectionProvider) {
    this.postCollectionProvider = postCollectionProvider;
    this.artistCollectionProvider = artistCollectionProvider;
    this.artCollectionProvider = artCollectionProvider;
    this.tagCollectionProvider = tagCollectionProvider;
    this.categoryCollectionProvider = categoryCollectionProvider;
    this.tagInPostCollectionProvider = tagInPostCollectionProvider;
    this.categoryInPostCollectionProvider = categoryInPostCollectionProvider;
  }

  @Override
  public PostInteractorImpl get() {
    return new PostInteractorImpl(
        postCollectionProvider.get(),
        artistCollectionProvider.get(),
        artCollectionProvider.get(),
        tagCollectionProvider.get(),
        categoryCollectionProvider.get(),
        tagInPostCollectionProvider.get(),
        categoryInPostCollectionProvider.get());
  }

  public static PostInteractorImpl_Factory create(
      Provider<PostCollection> postCollectionProvider,
      Provider<ArtistCollection> artistCollectionProvider,
      Provider<ArtCollection> artCollectionProvider,
      Provider<TagCollection> tagCollectionProvider,
      Provider<CategoryCollection> categoryCollectionProvider,
      Provider<TagInPostCollection> tagInPostCollectionProvider,
      Provider<CategoryInPostCollection> categoryInPostCollectionProvider) {
    return new PostInteractorImpl_Factory(
        postCollectionProvider,
        artistCollectionProvider,
        artCollectionProvider,
        tagCollectionProvider,
        categoryCollectionProvider,
        tagInPostCollectionProvider,
        categoryInPostCollectionProvider);
  }

  public static PostInteractorImpl newPostInteractorImpl(
      PostCollection postCollection,
      ArtistCollection artistCollection,
      ArtCollection artCollection,
      TagCollection tagCollection,
      CategoryCollection categoryCollection,
      TagInPostCollection tagInPostCollection,
      CategoryInPostCollection categoryInPostCollection) {
    return new PostInteractorImpl(
        postCollection,
        artistCollection,
        artCollection,
        tagCollection,
        categoryCollection,
        tagInPostCollection,
        categoryInPostCollection);
  }
}
