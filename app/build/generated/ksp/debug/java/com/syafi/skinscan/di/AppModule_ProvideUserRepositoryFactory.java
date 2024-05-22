package com.syafi.skinscan.di;

import com.syafi.skinscan.data.local.dataStore.UserSessionData;
import com.syafi.skinscan.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideUserRepositoryFactory implements Factory<UserRepository> {
  private final Provider<UserSessionData> prefProvider;

  public AppModule_ProvideUserRepositoryFactory(Provider<UserSessionData> prefProvider) {
    this.prefProvider = prefProvider;
  }

  @Override
  public UserRepository get() {
    return provideUserRepository(prefProvider.get());
  }

  public static AppModule_ProvideUserRepositoryFactory create(
      Provider<UserSessionData> prefProvider) {
    return new AppModule_ProvideUserRepositoryFactory(prefProvider);
  }

  public static UserRepository provideUserRepository(UserSessionData pref) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserRepository(pref));
  }
}
