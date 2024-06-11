package com.syafi.skinscan.data.repository

import android.util.Log
import com.google.gson.Gson
import com.syafi.skinscan.data.remote.ErrorParser
import com.syafi.skinscan.data.remote.api.DetectionService
import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse
import com.syafi.skinscan.data.remote.response.detection.prediction.PredictionResponse
import com.syafi.skinscan.domain.repository.IDetectionRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DetectionRepository @Inject constructor(
    private val api: DetectionService
) : IDetectionRepository {

    private val errorParser = ErrorParser(Gson())

    override suspend fun getAllDetectionResults(
        token: String,
        query: String?,
        limit: Int?,
        order: String?,
        status: String?,
        page: Int?
    ): DetectionResponse {
        val resp = api.getAllDetectionResult(
            token = token,
            query = query,
            limit = limit,
            order = order,
            status = status,
            page = page
        )

        if (resp.isSuccessful) {
            resp.body()?.let {
                return it
            }
        }

        val error = errorParser.parse((resp.errorBody()?.string()))
        throw Exception(error)
    }

    override suspend fun getDetectionDetail(token: String, id: String): DetailDetectionResponse {
        val resp = api.getDetectionDetail(token, id)

        if (resp.isSuccessful) {
            resp.body()?.let {
                return it
            }
        }

        val error = errorParser.parse((resp.errorBody()?.string()))
        throw Exception(error)
    }

    override suspend fun removeDetection(token: String, id: String): String {
        val resp = api.deleteDetection(token, id)

        if (resp.isSuccessful) {
            resp.body()?.let {
                return it.message
            }
        }
        val error = errorParser.parse((resp.errorBody()?.string()))
        throw Exception(error)
    }

    override suspend fun getPrediction(
        token: String,
        multipartBody: MultipartBody.Part,
        reqTitleBody: RequestBody
    ): PredictionResponse {

        val resp = api.getPrediction(token, multipartBody, reqTitleBody)

        if (resp.isSuccessful) {
            resp.body()?.let {
                Log.i("iddd", "getPrediction: ${it.data}")
                return it
            }
        }

        val error = errorParser.parse(resp.errorBody()?.string())
        throw Exception(error)
    }
}