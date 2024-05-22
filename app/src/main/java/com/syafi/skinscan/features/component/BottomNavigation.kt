package com.syafi.skinscan.features.component

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.syafi.skinscan.R
import com.syafi.skinscan.domain.model.BottomNavData
import com.syafi.skinscan.ui.theme.Neutral100
import com.syafi.skinscan.ui.theme.Neutral200
import com.syafi.skinscan.ui.theme.Neutral400
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route

@Composable
fun BottomNavigation(navController: NavController) {

    val bottomNavList= listOf(
        BottomNavData(
            stringResource(R.string.home),
            painterResource(id = R.drawable.ic_home),
            Route.HOME_SCREEN
        ),
        BottomNavData(
            stringResource(R.string.analyze),
            painterResource(id = R.drawable.ic_analyze),
            Route.ANALYZE_SCREEN
        ),
        BottomNavData(
            stringResource(R.string.history),
            painterResource(id = R.drawable.ic_history),
            Route.HISTORY_SCREEN
        ),
        BottomNavData(
            stringResource(R.string.profile),
            painterResource(id = R.drawable.ic_profile),
            Route.PROFILE_SCREEN
        ),
    )
    val currentScreen= navController.currentBackStackEntry?.destination?.route

    BottomAppBar(containerColor = Neutral100, tonalElevation = 12.dp) {
        bottomNavList.forEach {
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(it.route) },
                icon = { 
                    Icon(
                        painter = it.icon,
                        contentDescription = it.title,
                        tint = if (currentScreen.equals(it.route)) Primary700 else Neutral400
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        style = Type.text2xsRegular(),
                        color = if (currentScreen.equals(it.route)) Primary700 else Neutral400
                    )
                }
            )
        }
    }
}