package com.syafi.skinscan.data.repository

import com.google.gson.Gson
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.ErrorParser
import com.syafi.skinscan.data.remote.api.UserService
import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import com.syafi.skinscan.domain.repository.IUserRepository
import kotlin.Exception

class UserRepository(
    private val pref: UserSessionData,
    private val api: UserService
) : IUserRepository {

    val errorParser= ErrorParser(Gson())
    override suspend fun setUserSession(isCompleted: Boolean) {
        pref.setIsComplete(isCompleted)
    }

    override suspend fun register(registerRequest: RegisterRequest): AuthResponse {

        val resp= api.register(
            registerRequest.name.trim(),
            registerRequest.email.trim(),
            registerRequest.password.trim()
        )

        if (resp.isSuccessful) {
            resp.body()?.let {
                return it
            }
        }

        val error= errorParser.parse(resp.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun login(loginRequest: LoginRequest): AuthResponse {

        val resp= api.login(
            loginRequest.email.trim(),
            loginRequest.password.trim()
        )

        if (resp.isSuccessful) {
            resp.body()?.let {
                setUserToken(it.data.token)
                return it
            }
        }

        val error= errorParser.parse(resp.errorBody()?.string())
        throw Exception(error)
    }

    override suspend fun setUserToken(token: String) {
        pref.setUserToken(token)
    }

    override suspend fun getUserProfile(token: String): ProfileResponse {
        val resp= api.getProfile(token)

        if (resp.isSuccessful) {
            resp.body()?.let {
                return it
            }
        }

        val error= errorParser.parse((resp.errorBody()?.string()))
        throw Exception(error)
    }
}