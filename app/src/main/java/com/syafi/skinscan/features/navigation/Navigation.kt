package com.syafi.skinscan.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.syafi.skinscan.features.home.HomeScreen
import com.syafi.skinscan.features.analyze.AnalyzeScreen
import com.syafi.skinscan.features.component.detail.ResultDetail
import com.syafi.skinscan.features.edit.EditScreen
import com.syafi.skinscan.features.history.HistoryScreen
import com.syafi.skinscan.features.inspect.InspectImage
import com.syafi.skinscan.features.login.LoginScreen
import com.syafi.skinscan.features.profile.ProfileScreen
import com.syafi.skinscan.features.register.RegisterScreen
import com.syafi.skinscan.features.splash.SplashScreen
import com.syafi.skinscan.features.upload.UploadScreen
import com.syafi.skinscan.features.welcome.WelcomeScreen
import com.syafi.skinscan.util.Route

@Composable
fun Navigation(navController: NavHostController, setFabOnClick: ((() -> Unit)?) -> Unit) {

    NavHost(navController = navController, startDestination = Route.REGISTER_SCREEN) {

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
        composable(Route.HOME_SCREEN) {
            HomeScreen(navController)
        }
        composable(Route.ANALYZE_SCREEN) {
            AnalyzeScreen(navController, setFabOnClick)
        }
        composable<Route.UPLOAD_SCREEN> {
            val args= it.toRoute<Route.UPLOAD_SCREEN>()
            UploadScreen(navController = navController, photoUri = args.photoUri)
        }
        composable<Route.INSPECT_IMAGE> {
            val args= it.toRoute<Route.INSPECT_IMAGE>()
            InspectImage(navController = navController, photoUri = args.photoUri)
        }
        composable<Route.RESULT_DETAIL> {
            val args= it.toRoute<Route.RESULT_DETAIL>()
            ResultDetail(navController = navController)
        }
        composable(Route.HISTORY_SCREEN) {
            HistoryScreen(navController)
        }
        composable(Route.PROFILE_SCREEN) {
            ProfileScreen(navController)
        }
        composable<Route.EDIT_SCREEN> {
            val args= it.toRoute<Route.EDIT_SCREEN>()
            EditScreen(navController = navController, changeType = args.changeType)
        }
    }
}