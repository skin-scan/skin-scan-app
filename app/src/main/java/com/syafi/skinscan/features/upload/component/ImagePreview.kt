package com.syafi.skinscan.features.upload.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.upload.UploadViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary100
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route

@Composable
fun ImagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    photoUri: String?
) {


    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Primary100),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.image_preview),
            style = Type.textsmSemiBold(),
            color = Primary700,
            modifier = Modifier.padding(20.dp)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .background(Neutral50)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                model = photoUri?.toUri() ?: R.drawable.ic_launcher_background,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sizeIn(maxHeight = 65.dp, maxWidth = 65.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        navController.navigate(Route.INSPECT_IMAGE(photoUri = photoUri ?: ""))
                    }
            )
            Text(text = stringResource(R.string.photo_will_be_uploaded), style = Type.textxsMedium())
        }
    }
}