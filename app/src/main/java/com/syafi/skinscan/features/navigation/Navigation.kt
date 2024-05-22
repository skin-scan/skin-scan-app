package com.syafi.skinscan.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.syafi.skinscan.features.login.LoginScreen
import com.syafi.skinscan.features.register.RegisterScreen
import com.syafi.skinscan.features.splash.SplashScreen
import com.syafi.skinscan.features.welcome.WelcomeScreen
import com.syafi.skinscan.util.Route

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.SPLASH_SCREEN) {

        composable(Route.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(Route.WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }
        composable(Route.LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        composable(Route.REGISTER_SCREEN) {
            RegisterScreen(navController)
        }
    }
}