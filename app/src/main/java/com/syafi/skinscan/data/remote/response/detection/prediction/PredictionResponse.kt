package com.syafi.skinscan.data.remote.response.detection.prediction

data class PredictionResponse(
    val `data`: DiseaseInformation,
    val message: String,
    val status: Int
)