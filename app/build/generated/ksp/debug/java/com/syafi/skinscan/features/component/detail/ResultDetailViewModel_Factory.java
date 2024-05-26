package com.syafi.skinscan.features.component.detail;

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
public final class ResultDetailViewModel_Factory implements Factory<ResultDetailViewModel> {
  @Override
  public ResultDetailViewModel get() {
    return newInstance();
  }

  public static ResultDetailViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ResultDetailViewModel newInstance() {
    return new ResultDetailViewModel();
  }

  private static final class InstanceHolder {
    private static final ResultDetailViewModel_Factory INSTANCE = new ResultDetailViewModel_Factory();
  }
}
