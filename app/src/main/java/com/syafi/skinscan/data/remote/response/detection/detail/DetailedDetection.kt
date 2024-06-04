package com.syafi.skinscan.data.remote.response.detection.detail

data class DetailedDetection(
    val assessment: String,
    val commonName: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val medicalName: String,
    val status: String,
    val title: String
)