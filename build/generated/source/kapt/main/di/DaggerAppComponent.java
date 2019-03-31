package di;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.ArtCollection;
import database.collection.ArtistCollection;
import database.collection.AvatarCollection;
import database.collection.CategoryCollection;
import database.collection.CategoryInPostCollection;
import database.collection.CommentCollection;
import database.collection.PostCollection;
import database.collection.TagCollection;
import database.collection.TagInPostCollection;
import di.module.CollectionsModule;
import di.module.CollectionsModule_ProvideArtCollectionFactory;
import di.module.CollectionsModule_ProvideArtistCollectionFactory;
import di.module.CollectionsModule_ProvideAvatarCollectionFactory;
import di.module.CollectionsModule_ProvideCategoryCollectionFactory;
import di.module.CollectionsModule_ProvideCategoryInPostCollectionFactory;
import di.module.CollectionsModule_ProvideCommentCollectionFactory;
import di.module.CollectionsModule_ProvidePostCollectionFactory;
import di.module.CollectionsModule_ProvideTagCollectionFactory;
import di.module.CollectionsModule_ProvideTagInPosCollectionFactory;
import di.module.DatabaseModule;
import di.module.DatabaseModule_ProvideDatabaseFactory;
import interactor.comment.CommentInteractor;
import interactor.comment.CommentInteractorImpl;
import interactor.post.PostInteractor;
import interactor.post.PostInteractorImpl;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<Database> provideDatabaseProvider;

  private Provider<PostCollection> providePostCollectionProvider;

  private Provider<ArtistCollection> provideArtistCollectionProvider;

  private Provider<ArtCollection> provideArtCollectionProvider;

  private Provider<TagCollection> provideTagCollectionProvider;

  private Provider<CategoryCollection> provideCategoryCollectionProvider;

  private Provider<TagInPostCollection> provideTagInPosCollectionProvider;

  private Provider<CategoryInPostCollection> provideCategoryInPostCollectionProvider;

  private Provider<CommentCollection> provideCommentCollectionProvider;

  private Provider<AvatarCollection> provideAvatarCollectionProvider;

  private DaggerAppComponent(
      CollectionsModule collectionsModuleParam, DatabaseModule databaseModuleParam) {

    initialize(collectionsModuleParam, databaseModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  private PostInteractorImpl getPostInteractorImpl() {
    return new PostInteractorImpl(
        providePostCollectionProvider.get(),
        provideArtistCollectionProvider.get(),
        provideArtCollectionProvider.get(),
        provideTagCollectionProvider.get(),
        provideCategoryCollectionProvider.get(),
        provideTagInPosCollectionProvider.get(),
        provideCategoryInPostCollectionProvider.get());
  }

  private CommentInteractorImpl getCommentInteractorImpl() {
    return new CommentInteractorImpl(
        provideCommentCollectionProvider.get(),
        provideArtistCollectionProvider.get(),
        provideAvatarCollectionProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(
      final CollectionsModule collectionsModuleParam, final DatabaseModule databaseModuleParam) {
    this.provideDatabaseProvider =
        DoubleCheck.provider(DatabaseModule_ProvideDatabaseFactory.create(databaseModuleParam));
    this.providePostCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvidePostCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideArtistCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideArtistCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideArtCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideArtCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideTagCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideTagCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideCategoryCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideCategoryCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideTagInPosCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideTagInPosCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideCategoryInPostCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideCategoryInPostCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideCommentCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideCommentCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
    this.provideAvatarCollectionProvider =
        DoubleCheck.provider(
            CollectionsModule_ProvideAvatarCollectionFactory.create(
                collectionsModuleParam, provideDatabaseProvider));
  }

  @Override
  public PostInteractor postInteractor() {
    return getPostInteractorImpl();
  }

  @Override
  public CommentInteractor commentInteractor() {
    return getCommentInteractorImpl();
  }

  public static final class Builder {
    private CollectionsModule collectionsModule;

    private DatabaseModule databaseModule;

    private Builder() {}

    public Builder databaseModule(DatabaseModule databaseModule) {
      this.databaseModule = Preconditions.checkNotNull(databaseModule);
      return this;
    }

    public Builder collectionsModule(CollectionsModule collectionsModule) {
      this.collectionsModule = Preconditions.checkNotNull(collectionsModule);
      return this;
    }

    public AppComponent build() {
      if (collectionsModule == null) {
        this.collectionsModule = new CollectionsModule();
      }
      if (databaseModule == null) {
        this.databaseModule = new DatabaseModule();
      }
      return new DaggerAppComponent(collectionsModule, databaseModule);
    }
  }
}
