package com.syafi.skinscan.data.remote.response.auth.changePassword

data class ChangePasswordResponse(
    val userId: UserId,
    val message: String,
    val status: Int
)