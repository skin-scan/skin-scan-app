package com.syafi.skinscan.data.remote.api

import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.data.remote.response.detection.delete.DeleteResponse
import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse
import com.syafi.skinscan.data.remote.response.detection.prediction.PredictionResponse
import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
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

    @GET("detections/{id}")
    suspend fun getDetectionDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DetailDetectionResponse>

    @DELETE("detections/{id}")
    suspend fun deleteDetection(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteResponse>

    @Multipart
    @POST("detections")
    suspend fun getPrediction(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @Part("title") title: RequestBody,
    ): Response<PredictionResponse>

}