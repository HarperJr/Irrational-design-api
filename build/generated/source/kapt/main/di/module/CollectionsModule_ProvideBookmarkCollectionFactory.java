package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.BookmarkCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideBookmarkCollectionFactory
    implements Factory<BookmarkCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideBookmarkCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BookmarkCollection get() {
    return proxyProvideBookmarkCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideBookmarkCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideBookmarkCollectionFactory(module, databaseProvider);
  }

  public static BookmarkCollection proxyProvideBookmarkCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideBookmarkCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
