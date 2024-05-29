package com.syafi.skinscan.features.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun ProfileStats(value: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "200",
            style = Type.textlgBold(),
            color = Primary900
        )
        Text(
            text = stringResource(R.string.uploaded),
            style = Type.text2xsRegular(),
            color = Primary900
        )
    }
}