package com.syafi.skinscan.features.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType

@Composable
fun CustomOutlinedButton(onClick: () -> Unit, type: String, text: String, modifier: Modifier= Modifier) {
    when (type) {
        ButtonType.LARGE -> {
            OutlinedButton(
                onClick = { onClick() },
                border = BorderStroke(width = 2.dp, color = Primary700),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = text,
                    color = Primary700,
                    style = Type.textsmBold()
                )
            }
        }
        ButtonType.MEDIUM -> {
            OutlinedButton(
                onClick = { onClick() },
                border = BorderStroke(width = 2.dp, color = Primary700),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .width(152.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = text,
                    color = Primary700,
                    style = Type.textsmBold()
                )
            }
        }
        ButtonType.SMALL -> {
            OutlinedButton(
                onClick = { onClick() },
                border = BorderStroke(width = 2.dp, color = Primary700),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .height(30.dp)
            ) {
                Text(
                    text = text,
                    color = Primary700,
                    style = Type.textsmBold()
                )
            }
        }
    }
}