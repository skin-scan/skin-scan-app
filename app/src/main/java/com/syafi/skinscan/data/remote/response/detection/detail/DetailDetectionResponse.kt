package com.syafi.skinscan.data.remote.response.detection.detail

data class DetailDetectionResponse(
    val data: DetailedDetection,
    val message: String,
    val status: Int
)