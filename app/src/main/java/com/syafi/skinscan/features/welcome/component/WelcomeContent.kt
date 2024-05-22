package com.syafi.skinscan.features.welcome.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.features.welcome.welcomeScreenDataList
import com.syafi.skinscan.ui.theme.Neutral800
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun WelcomeContent(index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        AsyncImage(
            model = welcomeScreenDataList[index].screenImg,
            contentDescription = welcomeScreenDataList[index].screenTitle,
            modifier = Modifier.sizeIn(300.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = welcomeScreenDataList[index].screenTitle,
            textAlign = TextAlign.Center,
            color = Primary900,
            style = Type.textlgBold()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = welcomeScreenDataList[index].screenDesc,
            textAlign = TextAlign.Center,
            color = Neutral800,
            style = Type.textsmRegular(),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}