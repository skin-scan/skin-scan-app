package com.syafi.skinscan.di

import android.content.Context
import com.syafi.skinscan.data.local.dataStore.UserSessionData
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
    fun provideUserRepository(pref: UserSessionData): UserRepository {
        return UserRepository(pref)
    }
}