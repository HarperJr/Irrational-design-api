package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.FollowerCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideFollowerCollectionFactory
    implements Factory<FollowerCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideFollowerCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public FollowerCollection get() {
    return proxyProvideFollowerCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideFollowerCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideFollowerCollectionFactory(module, databaseProvider);
  }

  public static FollowerCollection proxyProvideFollowerCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideFollowerCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
