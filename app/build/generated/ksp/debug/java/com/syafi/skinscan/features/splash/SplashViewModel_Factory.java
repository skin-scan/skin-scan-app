package com.syafi.skinscan.features.splash;

import com.syafi.skinscan.data.local.dataStore.UserSessionData;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<UserSessionData> prefProvider;

  public SplashViewModel_Factory(Provider<UserSessionData> prefProvider) {
    this.prefProvider = prefProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(prefProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<UserSessionData> prefProvider) {
    return new SplashViewModel_Factory(prefProvider);
  }

  public static SplashViewModel newInstance(UserSessionData pref) {
    return new SplashViewModel(pref);
  }
}
