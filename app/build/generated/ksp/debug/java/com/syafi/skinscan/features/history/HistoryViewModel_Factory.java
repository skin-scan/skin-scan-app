package com.syafi.skinscan.features.history;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  @Override
  public HistoryViewModel get() {
    return newInstance();
  }

  public static HistoryViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HistoryViewModel newInstance() {
    return new HistoryViewModel();
  }

  private static final class InstanceHolder {
    private static final HistoryViewModel_Factory INSTANCE = new HistoryViewModel_Factory();
  }
}
