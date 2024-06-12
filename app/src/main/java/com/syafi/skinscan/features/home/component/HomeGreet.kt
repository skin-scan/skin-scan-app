package com.syafi.skinscan.features.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Type

@Composable
fun HomeGreet(name: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AsyncImage(
            model = R.drawable.img_buble2,
            contentDescription = "",
            modifier = Modifier
                .sizeIn(maxWidth = 160.dp, maxHeight = 160.dp)
                .padding()
                .align(Alignment.TopEnd)
        )

        Column(Modifier.padding(start = 20.dp, top= 50.dp).align(Alignment.TopStart)) {
            Text(
                text = stringResource(R.string.greet, name),
                color = Neutral50,
                style = Type.displayxsSemiBold(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(text = stringResource(R.string.are_ready), color = Neutral50, style = Type.textxsMedium())
        }
    }
}