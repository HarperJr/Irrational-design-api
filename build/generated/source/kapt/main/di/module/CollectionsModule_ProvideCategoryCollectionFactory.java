package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.CategoryCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideCategoryCollectionFactory
    implements Factory<CategoryCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideCategoryCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CategoryCollection get() {
    return proxyProvideCategoryCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideCategoryCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideCategoryCollectionFactory(module, databaseProvider);
  }

  public static CategoryCollection proxyProvideCategoryCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideCategoryCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
