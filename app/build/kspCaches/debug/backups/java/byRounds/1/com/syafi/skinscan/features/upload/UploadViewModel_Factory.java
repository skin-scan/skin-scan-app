package com.syafi.skinscan.features.upload;

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
public final class UploadViewModel_Factory implements Factory<UploadViewModel> {
  @Override
  public UploadViewModel get() {
    return newInstance();
  }

  public static UploadViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UploadViewModel newInstance() {
    return new UploadViewModel();
  }

  private static final class InstanceHolder {
    private static final UploadViewModel_Factory INSTANCE = new UploadViewModel_Factory();
  }
}
