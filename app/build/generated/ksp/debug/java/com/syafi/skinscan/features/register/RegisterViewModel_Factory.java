package com.syafi.skinscan.features.register;

import com.syafi.skinscan.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<UserRepository> repoProvider;

  public RegisterViewModel_Factory(Provider<UserRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public RegisterViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static RegisterViewModel_Factory create(Provider<UserRepository> repoProvider) {
    return new RegisterViewModel_Factory(repoProvider);
  }

  public static RegisterViewModel newInstance(UserRepository repo) {
    return new RegisterViewModel(repo);
  }
}
