package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Flow<Resource<AuthResponse>>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Flow<Resource<AuthResponse>>
}