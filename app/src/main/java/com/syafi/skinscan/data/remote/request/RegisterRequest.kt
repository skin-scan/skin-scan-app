package com.syafi.skinscan.data.remote.request

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String
)