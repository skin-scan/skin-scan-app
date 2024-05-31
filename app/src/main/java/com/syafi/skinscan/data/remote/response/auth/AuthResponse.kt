package com.syafi.skinscan.data.remote.response.auth

data class AuthResponse(
    val `data`: Data,
    val message: String,
    val status: Int
)