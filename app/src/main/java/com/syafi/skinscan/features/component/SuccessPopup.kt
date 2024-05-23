package com.syafi.skinscan.features.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType

@Composable
fun SuccessPopup(onButtonClick: () -> Unit, onDismiss: () -> Unit= {}) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Neutral50)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                AsyncImage(
                    model = R.drawable.img_success,
                    contentDescription = "",
                    modifier = Modifier.sizeIn(maxHeight = 150.dp)
                )
                Text(
                    text = stringResource(R.string.success),
                    color = Primary900,
                    style = Type.textlgSemiBold(),
                )
                Text(
                    text = stringResource(R.string.success_congrats),
                    color = Primary900,
                    style = Type.textxsRegular(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 50.dp)
                )
                CustomButton(
                    onClick = { onButtonClick() },
                    type = ButtonType.MEDIUM,
                    text = stringResource(R.string.ok)
                )
            }
        }
    }
}