package com.syafi.skinscan.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syafi.skinscan.features.component.CustomScaffold
import com.syafi.skinscan.features.navigation.Navigation
import com.syafi.skinscan.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val showBottomBar= listOf(
        Route.HOME_SCREEN,
        Route.ANALYZE_SCREEN,
        Route.HISTORY_SCREEN,
        Route.PROFILE_SCREEN
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomScaffold(
                        navController = navController,
                        showBottomBar = currentRoute in showBottomBar,
                        showFab = currentRoute.equals(Route.HOME_SCREEN)
                    ) {
                        Navigation(navController = navController)
                    }
                }
        }
    }
}
