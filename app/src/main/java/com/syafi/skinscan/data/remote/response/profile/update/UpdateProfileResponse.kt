package com.syafi.skinscan.data.remote.response.profile.update

data class UpdateProfileResponse(
    val `data`: UpdatedData,
    val message: String,
    val status: Int
)