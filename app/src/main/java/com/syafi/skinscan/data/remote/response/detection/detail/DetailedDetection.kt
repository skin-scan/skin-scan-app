package com.syafi.skinscan.data.remote.response.detection.detail

data class DetailedDetection(
    val assessment: Any,
    val createdAt: String,
    val diagnosis: String,
    val id: String,
    val image: String,
    val name: String,
    val status: String
)