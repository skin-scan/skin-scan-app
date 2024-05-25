package com.syafi.skinscan.features.splash

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Constant
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel= hiltViewModel()) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f,
            animationSpec = tween(
                Constant.ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        )
        delay(Constant.SPLASH_DURATION)
        navController.popBackStack()
        Log.i("lok", viewModel.destination.value)
        navController.navigate(viewModel.destination.value)
    }

    Column(
        Modifier
            .fillMaxSize()
            .alpha(alpha.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = R.drawable.ic_logo,
            contentDescription = "",
            modifier = Modifier.sizeIn(maxWidth = 200.dp, maxHeight = 200.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            Text(text = "Skin", style = Type.displaymdSemiBold(), color = Secondary500)
            Text(text = "Scan", style = Type.displaymdSemiBold(), color = Primary900)
        }
    }

}