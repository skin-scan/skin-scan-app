package com.syafi.skinscan.features.component.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.syafi.skinscan.features.component.view.Fab

@Composable
fun CustomScaffold(
    showBottomBar: Boolean = false,
    showFab: Boolean = false,
    navController: NavController,
    fabAction:() -> Unit? = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) },
        floatingActionButton = { if (showFab) Fab(onClick = { fabAction() }) },
        content = content
    )
}