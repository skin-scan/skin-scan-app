package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserService {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @Multipart
    @PUT("profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part?= null,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody
    ): Response<UpdateProfileResponse>
}