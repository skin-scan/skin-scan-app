package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral700
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Constant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanResultCard(
    photo: Any,
    timeStamp: String,
    medicalName: String,
    title: String,
    status: String,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Base50),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier
            .padding(end = 12.dp, bottom = 12.dp)
            .width(152.dp),
        onClick = { onClick() }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = photo,
                contentDescription = medicalName,
                modifier = Modifier
                    .size(135.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
        }

        Column(Modifier.padding(12.dp)) {

            Text(text = formatDate(timeStamp), style = Type.textxsRegular(), color = Neutral700)
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                style = Type.textsmMedium(),
                color = Neutral900,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))

            DiagnosedLabel(isDisease = status.lowercase(), result = medicalName)
        }
    }
}

private fun formatDate(timeStamp: String): String {
    val zonedDateTime = ZonedDateTime.parse(timeStamp, DateTimeFormatter.ISO_DATE_TIME)
    val customFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    return zonedDateTime.format(customFormatter)
}