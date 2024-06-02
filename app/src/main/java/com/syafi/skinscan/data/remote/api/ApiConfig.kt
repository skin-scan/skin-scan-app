package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val retrofit= Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi by lazy {
        retrofit.create(UserService::class.java)
    }

    val mainApi by lazy {
        retrofit.create(MainService::class.java)
    }
}