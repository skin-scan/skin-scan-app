package com.syafi.skinscan.features.analyze.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun GuidelineCard(modifier: Modifier = Modifier) {
    
    val guideline= stringArrayResource(id = R.array.guideline)
    
    Card(elevation = CardDefaults.elevatedCardElevation(16.dp)) {
        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.guideline),
                style = Type.textmdSemiBold(),
                color = Primary700,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Neutral50, RoundedCornerShape(10.dp))
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                guideline.forEachIndexed { index, guideline -> 
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "${index + 1}.", style = Type.textsmRegular())
                        Text(text = "$guideline\n", style = Type.textsmRegular())
                    }
                }
            }
        }
    }
}