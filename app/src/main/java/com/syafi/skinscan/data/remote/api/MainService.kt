package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MainService {

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>
}