package com.syafi.skinscan.data.remote.request

data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
)