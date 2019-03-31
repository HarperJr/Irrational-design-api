package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.AvatarCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideAvatarCollectionFactory
    implements Factory<AvatarCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideAvatarCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AvatarCollection get() {
    return proxyProvideAvatarCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideAvatarCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideAvatarCollectionFactory(module, databaseProvider);
  }

  public static AvatarCollection proxyProvideAvatarCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideAvatarCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
