package com.syafi.skinscan.di

import android.content.Context
import com.syafi.skinscan.BuildConfig
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.api.AuthService
import com.syafi.skinscan.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserSession(@ApplicationContext context: Context)= UserSessionData(context)

    @Provides
    @Singleton
    fun provideUserRepository(pref: UserSessionData, api: AuthService): UserRepository {
        return UserRepository(pref, api)
    }

    @Provides
    @Singleton
    fun provideAuthApi() =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
}