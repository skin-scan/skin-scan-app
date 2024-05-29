package com.syafi.skinscan.di;

import android.content.Context;
import com.syafi.skinscan.data.local.dataStore.UserSessionData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideUserSessionFactory implements Factory<UserSessionData> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideUserSessionFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public UserSessionData get() {
    return provideUserSession(contextProvider.get());
  }

  public static AppModule_ProvideUserSessionFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideUserSessionFactory(contextProvider);
  }

  public static UserSessionData provideUserSession(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserSession(context));
  }
}
