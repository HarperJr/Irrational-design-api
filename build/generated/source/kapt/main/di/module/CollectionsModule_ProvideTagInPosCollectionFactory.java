package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.TagInPostCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideTagInPosCollectionFactory
    implements Factory<TagInPostCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideTagInPosCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TagInPostCollection get() {
    return proxyProvideTagInPosCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideTagInPosCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideTagInPosCollectionFactory(module, databaseProvider);
  }

  public static TagInPostCollection proxyProvideTagInPosCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideTagInPosCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
