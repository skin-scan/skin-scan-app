package com.syafi.skinscan.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.syafi.skinscan.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.USER_DATASTORE)

class UserSessionData(context: Context) {

    private val dataStore = context.dataStore

    private val welcomeScreenCompletedKey = booleanPreferencesKey(Constant.WELCOME_SCREEN_KEY)
    private val userTokenKey = stringPreferencesKey(Constant.USER_TOKEN_KEY)

    val isWelcomeScreenCompleted: Flow<Boolean> =
        dataStore.data.map { pref ->
            pref[welcomeScreenCompletedKey] ?: false
        }

    val userToken: Flow<String> =
        dataStore.data.map { pref ->
            pref[userTokenKey] ?: ""
        }

    suspend fun setIsComplete(isCompleted: Boolean) {
        dataStore.edit { pref ->
            pref[welcomeScreenCompletedKey] = isCompleted
        }
    }

    suspend fun setUserToken(token: String) {
        dataStore.edit { pref ->
            pref[userTokenKey]= token
        }
    }
}