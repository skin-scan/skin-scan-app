package com.syafi.skinscan.features.analyze

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.MainActivity
import com.syafi.skinscan.features.analyze.component.ChooseMediaPicker
import com.syafi.skinscan.features.analyze.component.GuidelineCard
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type


@Composable
fun AnalyzeScreen() {

    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    MainActivity.fabAction = {
        isDialogOpen = true
    }

    if (isDialogOpen) {
        ChooseMediaPicker(
            onDismiss = { isDialogOpen = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary700)
    ) {

        Box(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.img_buble2,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .sizeIn(maxWidth = 160.dp, maxHeight = 160.dp)
                    .align(Alignment.TopEnd)

            )

            Column(
                Modifier.padding(top = 55.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.analyze),
                    style = Type.displaysmSemiBold(),
                    color = Neutral50,
                    modifier = Modifier.padding(start = 20.dp)
                )


                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Neutral50, RoundedCornerShape(30.dp))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.read_guideline),
                        style = Type.textsmRegular()
                    )
                    GuidelineCard(
                        Modifier
                            .fillMaxWidth()
                            .background(Primary50, RoundedCornerShape(10.dp))
                    )
                }
            }
        }
    }
}