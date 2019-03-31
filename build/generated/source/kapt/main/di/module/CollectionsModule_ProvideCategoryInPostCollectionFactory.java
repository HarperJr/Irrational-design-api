package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.CategoryInPostCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideCategoryInPostCollectionFactory
    implements Factory<CategoryInPostCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideCategoryInPostCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CategoryInPostCollection get() {
    return proxyProvideCategoryInPostCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideCategoryInPostCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideCategoryInPostCollectionFactory(module, databaseProvider);
  }

  public static CategoryInPostCollection proxyProvideCategoryInPostCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideCategoryInPostCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
