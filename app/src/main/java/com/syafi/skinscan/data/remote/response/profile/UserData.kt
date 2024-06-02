package com.syafi.skinscan.data.remote.response.profile

data class UserData(
    val email: String,
    val name: String,
    val profilePicture: Any,
    val summary: Summary
)