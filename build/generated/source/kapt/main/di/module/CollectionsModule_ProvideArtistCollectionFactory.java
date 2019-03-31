package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.ArtistCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideArtistCollectionFactory
    implements Factory<ArtistCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideArtistCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArtistCollection get() {
    return proxyProvideArtistCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideArtistCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideArtistCollectionFactory(module, databaseProvider);
  }

  public static ArtistCollection proxyProvideArtistCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideArtistCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
