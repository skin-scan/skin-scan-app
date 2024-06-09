package com.syafi.skinscan.features.edit.component

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.edit.EditViewModel
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import com.syafi.skinscan.util.reduceFileImage
import com.syafi.skinscan.util.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@Composable
fun EditProfile(
    navController: NavController,
    name: String,
    email: String,
    viewModel: EditViewModel,
) {

    val context= LocalContext.current
    val scope= rememberCoroutineScope()

    val token by viewModel.token
    var imageFile: File?
    var multipartBody: MultipartBody.Part?= null

    LaunchedEffect(key1 = true) {
        viewModel.getUserToken()
        viewModel.setName(name)
        viewModel.setEmail(email)
    }

    if (viewModel.isLoading.value) {
        Loading()
    }

    if (viewModel.isDialogOpen.value) {
        SuccessPopup(
            onButtonClick = {
                navController.popBackStack()
                navController.navigate(Route.PROFILE_SCREEN)
            },
            message = stringResource(R.string.successfully_edit_profile)
        )
    }

    val galleryIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.setPhotoUri(uri)
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.please_pick_a_picture),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Primary700),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .padding(top = 55.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Route.PROFILE_SCREEN)
                    },
                    Modifier.size(18.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        tint = Neutral50
                    )
                }
                Text(
                    text = stringResource(id = R.string.edit_profile),
                    color = Neutral50,
                    style = Type.displayxsBold(),
                )
            }

            AsyncImage(
                model = R.drawable.img_buble,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 40.dp)
                    .sizeIn(maxWidth = 160.dp, maxHeight = 160.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral50, RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
        ) {

            AsyncImage(
                model = viewModel.photoUri.value ?: R.drawable.profile_place_holder,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-45).dp)
                    .shadow(6.dp, CircleShape)
                    .border(width = 4.dp, color = Base50, shape = CircleShape)
                    .clickable {
                        galleryIntent.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            )

            AsyncImage(
                model = R.drawable.ic_blue_edit,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.TopCenter)
                    .offset(x = 30.dp, y = 20.dp)
                    .shadow(6.dp, CircleShape)
                    .clickable {
                        galleryIntent.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            )

            Column(Modifier.padding(top = 55.dp, end = 20.dp, start = 20.dp)) {

                Text(text = stringResource(R.string.name), style = Type.textsmMedium())
                CustomTextField(
                    text = viewModel.name.value,
                    onValueChange = {
                        viewModel.setName(it)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.email), style = Type.textsmMedium())
                CustomTextField(
                    text = viewModel.email.value,
                    onValueChange = {
                        viewModel.setEmail(it)
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))

                CustomButton(
                    onClick = {
                        if (isFormValid(viewModel, context)) {

                            val bearerToken= "Bearer $token"


                            viewModel.photoUri.value?.let {
                                imageFile= uriToFile(it, context).reduceFileImage()
                                val requestImageFile = imageFile?.asRequestBody("image/jpeg"
                                    .toMediaType())
                                multipartBody= MultipartBody.Part.createFormData(
                                    "image",
                                    imageFile?.name as String,
                                    requestImageFile as RequestBody
                                )
                            }

                            val name= viewModel.name.value
                            val email= viewModel.email.value

                            viewModel.setLoadingState(true)

                            val reqNameBody= name.toRequestBody("text/plain".toMediaType())
                            val reqEmailBody= email.toRequestBody("text/plain".toMediaType())

                            scope.launch {
                                viewModel.updateProfile(
                                    bearerToken,
                                    if (multipartBody == null) null else multipartBody,
                                    reqNameBody,
                                    reqEmailBody
                                ).collect {
                                    when (it) {
                                        is Resource.Error -> {
                                            viewModel.setLoadingState(false)
                                            showToast(context, it.message.toString())
                                        }
                                        is Resource.Loading -> viewModel.setLoadingState(true)
                                        is Resource.Success -> {
                                            viewModel.setLoadingState(false)
                                            viewModel.setDialogState(true)
                                        }
                                    }
                                }
                            }
                        }
                    },
                    type = ButtonType.LARGE,
                    text = stringResource(R.string.save)
                )
            }
        }
    }
}

private fun isFormValid(viewModel: EditViewModel, context: Context): Boolean {

    if (viewModel.name.value.isEmpty() || viewModel.email.value.isEmpty()) {
        showToast(context, context.getString(R.string.fill_all_the_field))
        return false
    }

    if (viewModel.name.value.length < 3) {
        showToast(context, context.getString(R.string.name_must_contain_3))
        return false
    }

    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(viewModel.email.value).matches()) {
        showToast(context, context.getString(R.string.email_not_valid))
        return false
    }
    return true
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}