package com.syafi.skinscan.features.component.view

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Secondary500

@Composable
fun Fab(onClick: () -> Unit= {}) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = Secondary500,
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = Neutral50)
    }
}