package com.syafi.skinscan.domain.model

import androidx.annotation.IdRes
import androidx.compose.ui.graphics.painter.Painter

data class BottomNavData(
    val title: String,
    val icon: Painter,
    val route: String
)
