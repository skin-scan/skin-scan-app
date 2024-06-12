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
        "Selamat Datang di SkinScan!",
        "SkinScan dikembangkan oleh spesialis IT dan ahli medis, menggunakan kecerdasan buatan (AI) canggih seperti dermatolog profesional, yang merevolusi penilaian kesehatan kulit."
    ),
    ScreenData(
        R.drawable.img_welcome2,
        "Sangat Mudah Digunakan",
        "Posisikan ponselmu di dekat area kulit yang bermasalah dan dapatkan penilaian menyeluruh. Tidak ada prosedur yang rumit, hanya mengandalkan di ujung jarimu saja."
    ),
    ScreenData(
        R.drawable.img_welcome3,
        "Fleksibel Menjaga Kulitmu",
        "Tetaplah proaktif dalam menjaga kesehatan kulitmu, bahkan saat Kamu sedang bepergian, dengan SkinScan yang dapat diakses di mana saja dan kapan saja."
    ),
)