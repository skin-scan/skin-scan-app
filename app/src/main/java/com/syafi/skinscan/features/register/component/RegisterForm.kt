package com.syafi.skinscan.features.register.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.register.RegisterViewModel
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.launch

@Composable
fun RegisterForm(navController: NavController, viewModel: RegisterViewModel) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {


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
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.password), style = Type.textsmMedium())
        CustomTextField(
            text = viewModel.password.value,
            onValueChange = {
                viewModel.setPassword(it)
            },
            isPassword = true,
            showPassword = viewModel.isShowPassword.value,
            onPasswordToggle = {
                viewModel.setPasswordVisibility(!viewModel.isShowPassword.value)
            },
            trailingIcon = Icons.Default.VisibilityOff
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.confirm_password), style = Type.textsmMedium())
        CustomTextField(
            text = viewModel.confirmPassword.value,
            trailingIcon = Icons.Default.VisibilityOff,
            isPassword = true,
            showPassword = viewModel.isShowConfirmPassword.value,
            onValueChange = {
                viewModel.setConfirmPassword(it)
            },
            onPasswordToggle = {
                viewModel.setConfirmPasswordVisibility(!viewModel.isShowConfirmPassword.value)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
                if (isFormValid(viewModel, context)) {
                    val req = RegisterRequest(
                        viewModel.name.value,
                        viewModel.email.value,
                        viewModel.password.value
                    )

                    scope.launch {
                        viewModel.register(req).collect {
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
            text = stringResource(R.string.register)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(R.string.have_account), style = Type.textxsRegular())
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.login),
                style = Type.textxsRegular(),
                color = Primary700,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(Route.LOGIN_SCREEN)
                }
            )
        }
    }
}

fun isFormValid(viewModel: RegisterViewModel, context: Context): Boolean {

    if (viewModel.name.value.isEmpty() ||
        viewModel.email.value.isEmpty() ||
        viewModel.password.value.isEmpty() ||
        viewModel.confirmPassword.value.isEmpty()
        ) {
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

    if (viewModel.password.value.length < 8) {
        showToast(context, context.getString(R.string.password_must_contain_8_characters))
        return false
    }

    if (viewModel.password.value != viewModel.confirmPassword.value) {
        showToast(context, context.getString(R.string.passwords_do_not_match))
        return false
    }

    return true
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}