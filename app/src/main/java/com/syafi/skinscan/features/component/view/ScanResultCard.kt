package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral700
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun ScanResultCard(photo: Any, timeStamp: String, name: String, output: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Base50),
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(152.dp),

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = photo,
                contentDescription = output,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(135.dp)
                    .padding(12.dp)
            )
        }

        Column(Modifier.padding(12.dp)) {

            Text(text = timeStamp, style = Type.textxsRegular(), color = Neutral700)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = name, style = Type.textsmMedium(), color = Neutral900)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = output, style = Type.textxsRegular(), color = Neutral700)
        }
    }
}