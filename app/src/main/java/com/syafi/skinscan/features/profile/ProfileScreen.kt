package com.syafi.skinscan.features.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.profile.UserData
import com.syafi.skinscan.features.component.dialog.ChoiceDialog
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.profile.component.ProfileAction
import com.syafi.skinscan.features.profile.component.ProfileHead
import com.syafi.skinscan.ui.theme.Neutral100
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.util.Constant
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel= hiltViewModel()) {

    val token by viewModel.token
    val safeDiagnosed by viewModel.safeDiagnosed
    val problemDiagnosed by viewModel.problemDiagnosed
    val userData by viewModel.userProfileData
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.token.value) {
        viewModel.setLoadingState(true)
        viewModel.getUserToken()


        token?.let {
            val bearerToken = "Bearer $it"
            getUserProfile(viewModel, bearerToken, context, this)
        }
    }

    if (viewModel.isDialogOpen.value) {
        ChoiceDialog(
            message = stringResource(id = R.string.want_to_logout), 
            onDismiss = { viewModel.setDialogState(false) },
            onNegativeClick = { viewModel.setDialogState(false) },
            onPositiveClick = {
                viewModel.setDialogState(false)
                navController.popBackStack()
                navController.navigate(Route.LOGIN_SCREEN)
            }
        )
    }

    if (viewModel.isLoading.value) {
        Loading()
    }
    
    Column(
        Modifier
            .fillMaxSize()
            .background(Neutral100),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(325.dp)
                .background(Primary700, RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
        ) {
            AsyncImage(
                model = R.drawable.img_circle,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )

            ProfileHead(userData, safeDiagnosed.toInt(), problemDiagnosed.toInt())
        }

        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            ProfileAction(
                icon = R.drawable.ic_edit,
                action = stringResource(R.string.edit_profile)
            ) {
                navController.navigate(Route.EDIT_SCREEN(Constant.EDIT_PROFILE))
            }

            ProfileAction(
                icon = R.drawable.ic_lock,
                action = stringResource(R.string.change_password)
            ) {
                navController.navigate(Route.EDIT_SCREEN(Constant.CHANGE_PASSWORD))
            }

            ProfileAction(
                icon = R.drawable.ic_globe,
                action = stringResource(R.string.change_language)
            ) {

            }

            ProfileAction(
                icon = R.drawable.ic_exit,
                action = stringResource(R.string.logout)
            ) {
                viewModel.setDialogState(true)
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun getUserProfile(
    viewModel: ProfileViewModel,
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