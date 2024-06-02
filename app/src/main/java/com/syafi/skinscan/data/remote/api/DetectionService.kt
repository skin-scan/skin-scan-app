package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DetectionService {

    @GET("detections")
    suspend fun getAllDetectionResult(
        @Header("Authorization") token: String,
        @Query("q") query: String?= null,
        @Query("limit") limit: Int?= null,
        @Query("oder") order: String?= null,
        @Query("status") status: String?= null,
        @Query("page") page: Int?= null
    ): Response<DetectionResponse>
}