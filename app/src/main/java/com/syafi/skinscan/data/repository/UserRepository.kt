package com.syafi.skinscan.data.repository

import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.domain.repository.IUserRepository

class UserRepository(
    private val pref: UserSessionData
): IUserRepository {
    override suspend fun setUserSession(isCompleted: Boolean) {
        pref.setIsComplete(isCompleted)
    }
}