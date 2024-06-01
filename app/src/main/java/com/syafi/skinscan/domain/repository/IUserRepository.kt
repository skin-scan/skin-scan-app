package com.syafi.skinscan.domain.repository

import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun setUserSession(isCompleted: Boolean)
    suspend fun register(registerRequest: RegisterRequest): AuthResponse
    suspend fun login (loginRequest: LoginRequest): AuthResponse

    suspend fun setUserToken(token: String)
}