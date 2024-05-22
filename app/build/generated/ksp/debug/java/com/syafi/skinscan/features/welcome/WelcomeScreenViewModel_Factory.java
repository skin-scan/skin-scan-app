package com.syafi.skinscan.features.welcome;

import com.syafi.skinscan.domain.useCase.user.SetUserSessionUseCase;
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
public final class WelcomeScreenViewModel_Factory implements Factory<WelcomeScreenViewModel> {
  private final Provider<SetUserSessionUseCase> setSessionUseCaseProvider;

  public WelcomeScreenViewModel_Factory(Provider<SetUserSessionUseCase> setSessionUseCaseProvider) {
    this.setSessionUseCaseProvider = setSessionUseCaseProvider;
  }

  @Override
  public WelcomeScreenViewModel get() {
    return newInstance(setSessionUseCaseProvider.get());
  }

  public static WelcomeScreenViewModel_Factory create(
      Provider<SetUserSessionUseCase> setSessionUseCaseProvider) {
    return new WelcomeScreenViewModel_Factory(setSessionUseCaseProvider);
  }

  public static WelcomeScreenViewModel newInstance(SetUserSessionUseCase setSessionUseCase) {
    return new WelcomeScreenViewModel(setSessionUseCase);
  }
}
