package com.syafi.skinscan.util

import kotlinx.serialization.Serializable

object Route {

    const val SPLASH_SCREEN= "splash_screen"
    const val WELCOME_SCREEN= "welcome_screen"
    const val LOGIN_SCREEN= "login_screen"
    const val REGISTER_SCREEN= "register_screen"
    const val HOME_SCREEN= "home_screen"
    const val ANALYZE_SCREEN= "analyze_screen"
    const val HISTORY_SCREEN= "history_screen"
    const val PROFILE_SCREEN= "profile_screen"

    @Serializable
    data class UPLOAD_SCREEN(
        val photoUri: String
    )

    @Serializable
    data class INSPECT_IMAGE(
        val photoUri: String
    )

    @Serializable
    data class RESULT_DETAIL(
        val id: String,
        val previousScreen: String
    )

    @Serializable
    data class EDIT_SCREEN(
        val changeType: String
    )
}