package com.syafi.skinscan.data.remote.response.detection

data class Detection(
    val createdAt: String,
    val diagnosis: String,
    val id: String,
    val image: String,
    val name: String,
    val status: String
)