package com.syafi.skinscan.features.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CustomScaffold(
    showBottomBar: Boolean= false,
    showFab: Boolean= false,
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {},
        floatingActionButton = {},
        content = content
    )
}