package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val retrofit= Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    val detectionApi: DetectionService by lazy {
        retrofit.create(DetectionService::class.java)
    }
}