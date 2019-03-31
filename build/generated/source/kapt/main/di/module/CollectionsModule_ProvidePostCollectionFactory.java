package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.PostCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvidePostCollectionFactory
    implements Factory<PostCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvidePostCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PostCollection get() {
    return proxyProvidePostCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvidePostCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvidePostCollectionFactory(module, databaseProvider);
  }

  public static PostCollection proxyProvidePostCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.providePostCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
