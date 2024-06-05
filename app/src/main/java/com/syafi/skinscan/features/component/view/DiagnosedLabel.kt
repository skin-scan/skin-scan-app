package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.ui.theme.Primary50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Constant

@Composable
fun DiagnosedLabel(isDisease: String, result: String) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    color =
                    when (isDisease) {
                        Constant.DIAGNOSED -> Secondary500
                        Constant.SAFE -> Primary700
                        else -> Primary50
                    },
                    shape = CircleShape
                )
        )
        Text(
            text = result,
            style = Type.textxsRegular(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}