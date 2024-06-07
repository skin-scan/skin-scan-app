package com.syafi.skinscan.domain.repository

import com.syafi.skinscan.data.remote.request.ChangePasswordRequest
import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.data.remote.response.auth.changePassword.ChangePasswordResponse
import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IUserRepository {

    suspend fun setUserSession(isCompleted: Boolean)
    suspend fun register(registerRequest: RegisterRequest): AuthResponse
    suspend fun login (loginRequest: LoginRequest): AuthResponse
    suspend fun setUserToken(token: String)
    suspend fun getUserProfile(token: String): ProfileResponse
    suspend fun updateUserProfile(
        token: String,
        multipartBody: MultipartBody.Part?,
        reqNameBody: RequestBody,
        reqEmailBody: RequestBody
    ): UpdateProfileResponse
    suspend fun changePassword(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): ChangePasswordResponse
}