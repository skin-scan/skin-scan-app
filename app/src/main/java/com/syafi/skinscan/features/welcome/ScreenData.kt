package com.syafi.skinscan.features.welcome

import androidx.annotation.IdRes
import androidx.compose.ui.res.integerResource
import com.syafi.skinscan.R

data class ScreenData(
    val screenImg: Int,
    val screenTitle: String,
    val screenDesc: String
)

val welcomeScreenDataList= listOf(
    ScreenData(
        R.drawable.img_welcome1,
        "Welcome to SkinScan",
        "Skinscan is developed by IT specialists and medical experts, matches professional dermatologist accuracy using advanced AI, revolutionizing skin health assessment."
    ),
    ScreenData(
        R.drawable.img_welcome2,
        "Easy as Can Be!",
        "Place your phone near the problem skin area for one minute, get a thorough assessment. No complicated procedures, just simplicity at your fingertips."
    ),
    ScreenData(
        R.drawable.img_welcome3,
        "Healthcare Anywhere!",
        "SkinScan is accessible anytime, anywhere. Stay proactive about your skin health, even while on the go. Plus, our unbeatable pricing plans ensure quality care without breaking the bank."
    ),
)