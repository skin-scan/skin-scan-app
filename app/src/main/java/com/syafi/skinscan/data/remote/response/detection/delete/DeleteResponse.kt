package com.syafi.skinscan.data.remote.response.detection.delete

data class DeleteResponse(
    val `data`: Data,
    val message: String,
    val status: Int
)