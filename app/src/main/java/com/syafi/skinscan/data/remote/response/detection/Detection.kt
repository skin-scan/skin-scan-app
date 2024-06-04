package com.syafi.skinscan.data.remote.response.detection

data class Detection(
    val commonName: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val medicalName: String,
    val status: String,
    val title: String
)