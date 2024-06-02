package com.syafi.skinscan.data.remote.response.detection

data class DetectionResponse(
    val `data`: List<Detection>,
    val message: String,
    val status: Int
)