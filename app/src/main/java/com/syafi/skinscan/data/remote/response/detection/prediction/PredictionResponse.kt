package com.syafi.skinscan.data.remote.response.detection.prediction

data class PredictionResponse(
    val predictionResult: PredictionResult,
    val message: String,
    val status: Int
)