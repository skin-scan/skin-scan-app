package com.syafi.skinscan.features.analyze;

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
public final class AnalyzeViewModel_Factory implements Factory<AnalyzeViewModel> {
  @Override
  public AnalyzeViewModel get() {
    return newInstance();
  }

  public static AnalyzeViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AnalyzeViewModel newInstance() {
    return new AnalyzeViewModel();
  }

  private static final class InstanceHolder {
    private static final AnalyzeViewModel_Factory INSTANCE = new AnalyzeViewModel_Factory();
  }
}
