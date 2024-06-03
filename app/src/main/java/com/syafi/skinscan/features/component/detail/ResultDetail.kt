package com.syafi.skinscan.features.component.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.detection.detail.DetailedDetection
import com.syafi.skinscan.features.component.dialog.ChoiceDialog
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.home.HomeViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary100
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ResultDetail(
    navController: NavController,
    id: String,
    previousScreen: String,
    viewModel: ResultDetailViewModel = hiltViewModel(),
) {

    val token by viewModel.token
    val detectionData by viewModel.detectionData
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.token.value) {
        viewModel.setLoadingState(true)
        viewModel.getUserToken()

        token?.let {
            val bearerToken = "Bearer $it"

            getDetectionDetail(viewModel, bearerToken, id, context, this)
        }
    }

    if (viewModel.isLoading.value) {
        Loading()
    }

    if (viewModel.isDeleteDialogOpen.value) {
        ChoiceDialog(
            stringResource(id = R.string.do_you_really_want_to_delete_it),
            onDismiss = { viewModel.setDeleteDialogState(false) },
            onPositiveClick = {
                viewModel.setDeleteDialogState(false)
                viewModel.setSuccessDialogState(true)
            },
            onNegativeClick = {
                viewModel.setDeleteDialogState(false)
            }
        )
    }

    if (viewModel.isSuccessDialogOpen.value) {
        SuccessPopup(
            onButtonClick = {
                navController.popBackStack()
                navController.navigate(Route.HISTORY_SCREEN)
            },
            message = stringResource(R.string.delete_success)
        )
    }

    Box(Modifier.fillMaxSize()) {

        AsyncImage(
            model = detectionData?.image ?: "",
            contentDescription = detectionData?.name.toString(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )

        IconButton(
            onClick = {
                if (previousScreen.equals(Route.HOME_SCREEN)) {
                    navController.popBackStack()
                    navController.navigate(Route.HOME_SCREEN)
                } else {
                    navController.popBackStack()
                    navController.navigate(Route.HISTORY_SCREEN)
                }
            },
            Modifier
                .padding(top = 55.dp, start = 20.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor = Neutral50)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "",
            )
        }

        Card(
            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Neutral50),
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .align(Alignment.BottomCenter)
                .offset(y = 60.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Primary100),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = detectionData?.name ?: "",
                    style = Type.textmdSemiBold(),
                    color = Primary700,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Neutral50)
                        .padding(20.dp),
                ) {
                    Text(text = stringResource(R.string.result), style = Type.textsmSemiBold())
                    Text(text = detectionData?.diagnosis ?: "", style = Type.textsmRegular())
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.risk_assessment), style = Type
                            .textsmSemiBold()
                    )
                    Text(
                        text = detectionData?.assessment.toString(),
                        style = Type.textsmRegular()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.time_detection),
                        style = Type.textsmSemiBold()
                    )
                    Text(
                        text =
                        if (detectionData?.createdAt != null) {
                            formatDate(detectionData?.createdAt.toString())
                        } else {
                            ""
                        },
                        style = Type.textsmRegular()
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            CustomButton(
                onClick = { viewModel.setDeleteDialogState(true) },
                type = ButtonType.LARGE,
                text = stringResource(R.string.delete),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun getDetectionDetail(
    viewModel: ResultDetailViewModel,
    token: String,
    id: String,
    context: Context,
    scope: CoroutineScope
) {
    scope.launch {
        viewModel.getDetectionDetail(token, id).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(context, it.message.toString())
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setDetectionData(it.data?.data as DetailedDetection)
                }
            }
        }
    }
}

private fun formatDate(timeStamp: String): String {
    val zonedDateTime = ZonedDateTime.parse(timeStamp, DateTimeFormatter.ISO_DATE_TIME)
    val customFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    return zonedDateTime.format(customFormatter)
}