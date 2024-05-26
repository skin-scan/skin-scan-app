package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType

@Composable
fun CustomButton(onClick: () -> Unit, type: String, text: String, modifier: Modifier= Modifier) {
    when (type) {
        ButtonType.LARGE -> {
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary700
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = text,
                    color = Neutral50,
                    style = Type.textsmBold()
                )
            }
        }
        ButtonType.MEDIUM -> {
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary700
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .width(152.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = text,
                    color = Neutral50,
                    style = Type.textsmBold()
                )
            }
        }
        ButtonType.SMALL -> {
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary700
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .height(30.dp)
            ) {
                Text(
                    text = text,
                    color = Neutral50,
                    style = Type.textsmBold()
                )
            }
        }
    }
}