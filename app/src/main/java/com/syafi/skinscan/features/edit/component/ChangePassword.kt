package com.syafi.skinscan.features.edit.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.request.ChangePasswordRequest
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.edit.EditViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.launch

@Composable
fun ChangePassword(navController: NavController, viewModel: EditViewModel) {

    val context= LocalContext.current
    val scope= rememberCoroutineScope()

    val token by viewModel.token

    LaunchedEffect(key1 = true) {
        viewModel.getUserToken()
    }

    if (viewModel.isLoading.value) {
        Loading()
    }

    if (viewModel.isDialogOpen.value) {
        SuccessPopup(
            onButtonClick = {
                viewModel.setDialogState(false)
                navController.popBackStack()
                navController.navigate(Route.PROFILE_SCREEN)
            },
            message = stringResource(R.string.password_changed)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary700)
    ) {
        AsyncImage(
            model = R.drawable.img_buble,
            contentDescription = "",
            modifier = Modifier
                .sizeIn(maxWidth = 160.dp, maxHeight = 160.dp)
                .align(Alignment.TopEnd)
        )

        Column {
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
                    text = stringResource(id = R.string.change_password),
                    color = Neutral50,
                    style = Type.displayxsBold(),
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        Neutral50,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.fill_to_change_password),
                    style = Type.textsmRegular()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = stringResource(R.string.old_password), style = Type.textsmMedium())
                CustomTextField(
                    text = viewModel.oldPassword.value,
                    onValueChange = {
                        viewModel.setOldPassword(it)
                    },
                    isPassword = true,
                    showPassword = viewModel.isShowOldPassword.value,
                    onPasswordToggle = {
                        viewModel.setOldPasswordVisibility(!viewModel.isShowOldPassword.value)
                    },
                    trailingIcon = Icons.Default.VisibilityOff
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.new_password), style = Type.textsmMedium())
                CustomTextField(
                    text = viewModel.newPassword.value,
                    onValueChange = {
                        viewModel.setNewPassword(it)
                    },
                    isPassword = true,
                    showPassword = viewModel.isShowNewPassword.value,
                    onPasswordToggle = {
                        viewModel.setNewPasswordVisibility(!viewModel.isShowNewPassword.value)
                    },
                    trailingIcon = Icons.Default.VisibilityOff
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.confirm_password), style = Type.textsmMedium())
                CustomTextField(
                    text = viewModel.confirmNewPassword.value,
                    onValueChange = {
                        viewModel.setConfirmNewPassword(it)
                    },
                    isPassword = true,
                    showPassword = viewModel.isShowNewConfirmPassword.value,
                    onPasswordToggle = {
                        viewModel.setNewConfirmPasswordVisibility(!viewModel.isShowNewConfirmPassword.value)
                    },
                    trailingIcon = Icons.Default.VisibilityOff
                )
                Spacer(modifier = Modifier.height(32.dp))

                CustomButton(
                    onClick = {
                        if (isFormValid(viewModel, context)) {

                            val oldPassword= viewModel.oldPassword.value
                            val newPassword= viewModel.newPassword.value
                            val bearerToken= "Bearer $token"

                            val changePasswordRequest= ChangePasswordRequest(
                                oldPassword, newPassword
                            )

                            scope.launch {
                                viewModel.changePassword(bearerToken, changePasswordRequest)
                                    .collect {
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

    if (viewModel.oldPassword.value.isEmpty() ||
        viewModel.newPassword.value.isEmpty() ||
        viewModel.confirmNewPassword.value.isEmpty()
    ) {
        showToast(
            context,
            context.getString(R.string.fill_all_the_field)
        )
        return false
    }

    if (viewModel.oldPassword.value.length < 8 ||
        viewModel.newPassword.value.length < 8 ||
        viewModel.confirmNewPassword.value.length < 8
        ) {
        showToast(
            context,
            context.getString(R.string.password_must_contain_8_characters)
        )
        return false
    }

    if (viewModel.newPassword.value != viewModel.confirmNewPassword.value) {
        showToast(
            context,
            context.getString(R.string.passwords_do_not_match)
        )
        return false
    }

    return true
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}