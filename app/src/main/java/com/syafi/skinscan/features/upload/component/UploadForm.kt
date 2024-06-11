package com.syafi.skinscan.features.upload.component

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.component.view.PleaseWait
import com.syafi.skinscan.features.upload.UploadViewModel
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import com.syafi.skinscan.util.reduceFileImage
import com.syafi.skinscan.util.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun UploadForm(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: UploadViewModel
) {

    val photoUri by viewModel.photoUri
    val token by viewModel.token

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getUserToken()
    }

    if (viewModel.isLoading.value) {
        PleaseWait()
    }

    if (viewModel.isShowDialog.value) {
        SuccessPopup(
            onButtonClick = {
                navController.popBackStack()

            },
            message = stringResource(R.string.prediction_success)
        )
    }

    Column(modifier) {
        Text(text = stringResource(R.string.upload_title), style = Type.textmdRegular())
        Spacer(modifier = Modifier.height(20.dp))

        ImagePreview(modifier = Modifier.fillMaxWidth(), navController, photoUri)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(R.string.photo_label), style = Type.textsmMedium())
        CustomTextField(
            text = viewModel.title.value,
            onValueChange = {
                viewModel.setTitle(it)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
                if (isFormValid(viewModel, context)) {

                    val bearerToken = "Bearer $token"

                    val imageFire = uriToFile(
                        viewModel.photoUri.value?.toUri() as Uri,
                        context
                    ).reduceFileImage()
                    val title = viewModel.title.value

                    val reqImageFile = imageFire.asRequestBody("image/jpeg".toMediaType())
                    val reqTitleBody = title.toRequestBody("text/plain".toMediaType())
                    val multipartBody = MultipartBody.Part.createFormData(
                        "image",
                        imageFire.name,
                        reqImageFile
                    )

                    scope.launch {
                        viewModel.getPrediction(bearerToken, multipartBody, reqTitleBody)

                        viewModel.predictionResponse.value?.collect {
                            when (it) {
                                is Resource.Error -> {
                                    viewModel.setLoadingState(false)
                                    showToast(context, it.message.toString())
                                }
                                is Resource.Loading -> viewModel.setLoadingState(true)
                                is Resource.Success -> {
                                    viewModel.setLoadingState(false)
                                    val id= it.data?.data?.id.toString()
                                    navController.popBackStack()
                                    navController.navigate(Route.RESULT_DETAIL(
                                        id,
                                        Route.HISTORY_SCREEN
                                    ))
                                }
                            }
                        }
                    }
                }
            },
            type = ButtonType.LARGE,
            text = stringResource(R.string.upload)
        )
    }
}

private fun isFormValid(viewModel: UploadViewModel, context: Context): Boolean {
    if (viewModel.title.value.isEmpty() || viewModel.photoUri.value == null) {
        showToast(context, context.getString(R.string.fill_all_the_field))
        return false
    }

    if (viewModel.title.value.length < 3) {
        showToast(context, context.getString(R.string.title_must_contain_3))
        return false
    }

    return true
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}