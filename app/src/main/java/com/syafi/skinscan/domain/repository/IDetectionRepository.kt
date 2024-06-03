package com.syafi.skinscan.domain.repository

import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse

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
}