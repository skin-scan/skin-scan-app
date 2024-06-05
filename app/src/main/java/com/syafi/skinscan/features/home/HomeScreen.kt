package com.syafi.skinscan.features.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.syafi.skinscan.data.remote.response.detection.Detection
import com.syafi.skinscan.data.remote.response.profile.UserData
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.home.component.HomeChartCard
import com.syafi.skinscan.features.home.component.HomeContent
import com.syafi.skinscan.features.home.component.HomeGreet
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary200
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val token by viewModel.token
    val safeDiagnosed by viewModel.safeDiagnosed
    val problemDiagnosed by viewModel.problemDiagnosed
    val userData by viewModel.userProfileData
    val detectionList by viewModel.detectionList

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.token.value) {
        viewModel.setLoadingState(true)
        viewModel.getUserToken()


        token?.let {
            val bearerToken = "Bearer $it"
            getUserProfile(viewModel, bearerToken, context, this)
            getDetectionResult(viewModel, bearerToken, context, this)
        }
    }

    if (viewModel.isLoading.value) {
        Loading()
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Primary200),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ) {
        item {
            Box() {
                HomeGreet(
                    name = userData?.name.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(204.dp)
                        .background(
                            Primary700,
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        )
                        .padding(end = 20.dp)
                )

                HomeChartCard(
                    safeDiagnosed,
                    problemDiagnosed,
                    Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 75.dp)
                )
            }
        }

        item {
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Neutral50, RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)),
                navController,
                detectionList
            )
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun getUserProfile(
    viewModel: HomeViewModel,
    token: String,
    context: Context,
    scope: CoroutineScope
) {
    scope.launch {
        viewModel.getUserProfile(token).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(context, it.message.toString())
                }
                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setUserProfileData(it.data?.data as UserData)
                }
            }
        }
    }
}

private fun getDetectionResult(
    viewModel: HomeViewModel,
    token: String,
    context: Context,
    scope: CoroutineScope
) {
    scope.launch {
        viewModel.getDetectionResult(
            token = token,
            query = null,
            limit = 5,
            order = null,
            status = null,
            page = null
        ).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(context, it.message.toString())
                }
                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setDetectionList(it.data?.data as List<Detection>)
                }
            }
        }
    }
}