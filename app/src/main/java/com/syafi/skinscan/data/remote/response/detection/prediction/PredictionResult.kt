package com.syafi.skinscan.data.remote.response.detection.prediction

data class PredictionResult(
    val assessment: String,
    val commonName: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val medicalName: String,
    val title: String
)