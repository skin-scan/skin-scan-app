package com.syafi.skinscan.features.component.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.syafi.skinscan.ui.theme.Primary700

@Composable
fun Loading() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        CircularProgressIndicator(color = Primary700)
    }
}