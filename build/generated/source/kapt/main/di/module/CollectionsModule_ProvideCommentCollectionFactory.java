package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import database.collection.CommentCollection;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CollectionsModule_ProvideCommentCollectionFactory
    implements Factory<CommentCollection> {
  private final CollectionsModule module;

  private final Provider<Database> databaseProvider;

  public CollectionsModule_ProvideCommentCollectionFactory(
      CollectionsModule module, Provider<Database> databaseProvider) {
    this.module = module;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CommentCollection get() {
    return proxyProvideCommentCollection(module, databaseProvider.get());
  }

  public static CollectionsModule_ProvideCommentCollectionFactory create(
      CollectionsModule module, Provider<Database> databaseProvider) {
    return new CollectionsModule_ProvideCommentCollectionFactory(module, databaseProvider);
  }

  public static CommentCollection proxyProvideCommentCollection(
      CollectionsModule instance, Database database) {
    return Preconditions.checkNotNull(
        instance.provideCommentCollection(database),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
