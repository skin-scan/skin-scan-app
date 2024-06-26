package com.syafi.skinscan.di

import android.content.Context
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.api.ApiConfig
import com.syafi.skinscan.data.remote.api.UserService
import com.syafi.skinscan.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserSession(@ApplicationContext context: Context)= UserSessionData(context)

    @Provides
    @Singleton
    fun provideUserRepository(pref: UserSessionData, api: UserService): UserRepository {
        return UserRepository(pref, api)
    }

    @Provides
    @Singleton
    fun provideAuthApi() = ApiConfig.userApi

    @Provides
    @Singleton
    fun provideMainApi()= ApiConfig.detectionApi
}