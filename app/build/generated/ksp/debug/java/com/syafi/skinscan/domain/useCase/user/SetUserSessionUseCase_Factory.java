package com.syafi.skinscan.domain.useCase.user;

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
public final class SetUserSessionUseCase_Factory implements Factory<SetUserSessionUseCase> {
  private final Provider<UserRepository> repoProvider;

  public SetUserSessionUseCase_Factory(Provider<UserRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SetUserSessionUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static SetUserSessionUseCase_Factory create(Provider<UserRepository> repoProvider) {
    return new SetUserSessionUseCase_Factory(repoProvider);
  }

  public static SetUserSessionUseCase newInstance(UserRepository repo) {
    return new SetUserSessionUseCase(repo);
  }
}
