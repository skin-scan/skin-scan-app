package com.syafi.skinscan.data.repository

import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.api.AuthService
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.domain.repository.IUserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val pref: UserSessionData,
    private val api: AuthService
): IUserRepository {
    override suspend fun setUserSession(isCompleted: Boolean) {
        pref.setIsComplete(isCompleted)
    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<AuthResponse>> =
        flow {
            emit(Resource.Loading())


        }
}