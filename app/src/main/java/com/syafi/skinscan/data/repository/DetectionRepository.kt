package com.syafi.skinscan.data.repository

import com.google.gson.Gson
import com.syafi.skinscan.data.remote.ErrorParser
import com.syafi.skinscan.data.remote.api.DetectionService
import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.domain.repository.IDetectionRepository
import javax.inject.Inject

class DetectionRepository @Inject constructor(
    private val api: DetectionService
): IDetectionRepository {

    val errorParser= ErrorParser(Gson())

    override suspend fun getAllDetectionResults(
        token: String,
        query: String?,
        limit: Int?,
        order: String?,
        status: String?,
        page: Int?
    ): DetectionResponse {
        val resp= api.getAllDetectionResult(
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

        val error= errorParser.parse((resp.errorBody()?.string()))
        throw Exception(error)
    }
}