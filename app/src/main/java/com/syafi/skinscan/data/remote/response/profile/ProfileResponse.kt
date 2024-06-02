package com.syafi.skinscan.data.remote.response.profile

data class ProfileResponse(
    val `data`: UserData,
    val message: String,
    val status: Int
)