package com.syafi.skinscan.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun ChartInfo(diagnosedProblem: Int, safeDiagnosed: Int) {
    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.photo_uploaded),
            color = Primary900,
            style = Type.textsmBold()
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = R.drawable.ic_warning,
                    contentDescription = "",
                    modifier = Modifier.sizeIn(maxHeight = 26.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = stringResource(id = R.string.diagnosed_problem),
                        style = Type.text2xsRegular(),
                        color = Neutral900
                    )
                    Text(text = diagnosedProblem.toString(), style = Type.textsmBold(), color = Neutral900)
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = R.drawable.ic_safe,
                    contentDescription = "",
                    modifier = Modifier.sizeIn(maxHeight = 26.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = stringResource(id = R.string.without_problem),
                        style = Type.text2xsRegular(),
                        color = Neutral900
                    )
                    Text(text = safeDiagnosed.toString(), style = Type.textsmBold(), color = Neutral900)
                }
            }
        }
    }
}