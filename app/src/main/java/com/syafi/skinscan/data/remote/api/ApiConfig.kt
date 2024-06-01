package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val retrofit= Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi by lazy {
        retrofit.create(AuthService::class.java)
    }
}