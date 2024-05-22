package com.syafi.skinscan.features.welcome.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.features.welcome.welcomeScreenDataList
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.ui.theme.Neutral300

@Composable
fun PageIndicator(screenPosition: Int, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        welcomeScreenDataList.forEachIndexed { index, screenData ->
            Box(
                modifier = Modifier
                    .size(
                        width = if (screenPosition == index) 30.dp else 10.dp,
                        height = 6.dp
                    )
                    .background(if (screenPosition == index) Secondary500 else Neutral300, CircleShape)

            )
        }
    }
}