package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun PleaseWait() {
    Dialog(onDismissRequest = {}) {
        Column(
            Modifier
                .background(Base50, RoundedCornerShape(25.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Primary700)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = stringResource(R.string.please_wait), style = Type.textlgSemiBold())
        }
    }
}