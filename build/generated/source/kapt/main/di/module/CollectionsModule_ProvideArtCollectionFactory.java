package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.ArtCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideArtCollectionFactory implements Factory<ArtCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideArtCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArtCollection get() {
    return proxyProvideArtCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideArtCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideArtCollectionFactory(module, databaseProvider);
  }

  public static ArtCollection proxyProvideArtCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideArtCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
