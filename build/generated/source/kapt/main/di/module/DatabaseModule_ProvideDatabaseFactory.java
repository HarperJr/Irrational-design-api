package di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import database.Database;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DatabaseModule_ProvideDatabaseFactory implements Factory<Database> {
  private final DatabaseModule module;

  public DatabaseModule_ProvideDatabaseFactory(DatabaseModule module) {
    this.module = module;
  }

  @Override
  public Database get() {
    return proxyProvideDatabase(module);
  }

  public static DatabaseModule_ProvideDatabaseFactory create(DatabaseModule module) {
    return new DatabaseModule_ProvideDatabaseFactory(module);
  }

  public static Database proxyProvideDatabase(DatabaseModule instance) {
    return Preconditions.checkNotNull(
        instance.provideDatabase(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
