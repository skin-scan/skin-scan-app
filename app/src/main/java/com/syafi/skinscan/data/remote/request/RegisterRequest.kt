package com.syafi.skinscan.data.remote.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)