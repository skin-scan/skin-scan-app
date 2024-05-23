package com.syafi.skinscan.features.analyze.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun ChooseMediaPicker(
    onDismiss: () -> Unit = {},
    onCameraClick: () -> Unit = {},
    onGalleryClick: () -> Unit = {}
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(16.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(300.dp),
            colors = CardDefaults.cardColors(containerColor = Primary700)
        ) {
            Text(
                text = stringResource(R.string.choose_image_source),
                color = Neutral50,
                style = Type.textmdSemiBold(),
                modifier = Modifier.padding(20.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Neutral50)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Neutral50)
                        .clickable {
                            onCameraClick()
                        },
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_camera,
                        contentDescription = "",
                        modifier = Modifier.sizeIn(maxHeight = 35.dp)
                    )
                    Text(text = stringResource(R.string.camera), style = Type.textmdMedium())
                }
                Divider()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Neutral50)
                        .clickable {
                            onGalleryClick()
                        },
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_gallery,
                        contentDescription = "",
                        modifier = Modifier.sizeIn(maxHeight = 35.dp)
                    )
                    Text(text = stringResource(R.string.gallery), style = Type.textmdMedium())
                }
            }
        }
    }

}