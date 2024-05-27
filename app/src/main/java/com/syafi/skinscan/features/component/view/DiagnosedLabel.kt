package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.ui.theme.Type

@Composable
fun DiagnosedLabel(isDisease: Boolean, result: String) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = if (isDisease) Secondary500 else Primary700,
                    shape = CircleShape
                )
        )
        Text(
            text = result,
            style = Type.text2xsRegular()
        )
    }
}