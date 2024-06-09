package com.syafi.skinscan.domain.repository

import com.syafi.skinscan.data.remote.request.ChangePasswordRequest
import com.syafi.skinscan.data.remote.response.auth.changePassword.ChangePasswordResponse
import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse
import com.syafi.skinscan.data.remote.response.detection.prediction.PredictionResponse
import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IDetectionRepository {

    suspend fun getAllDetectionResults(
        token: String,
        query: String?= null,
        limit: Int?= null,
        order: String?= null,
        status: String?= null,
        page: Int?= null
    ): DetectionResponse

    suspend fun getDetectionDetail(token: String, id: String): DetailDetectionResponse
    suspend fun removeDetection(token: String, id: String): String

    suspend fun getPrediction(
        token: String,
        multipartBody: MultipartBody.Part,
        reqTitleBody: RequestBody,
    ): PredictionResponse
}