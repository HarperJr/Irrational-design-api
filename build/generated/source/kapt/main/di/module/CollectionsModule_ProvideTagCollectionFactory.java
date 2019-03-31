package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.TagCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideTagCollectionFactory implements Factory<TagCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideTagCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TagCollection get() {
    return proxyProvideTagCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideTagCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideTagCollectionFactory(module, databaseProvider);
  }

  public static TagCollection proxyProvideTagCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideTagCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
