package com.syafi.skinscan.features.login.component

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
import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.login.LoginViewModel
import com.syafi.skinscan.features.register.RegisterViewModel
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Resource
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.launch

@Composable
fun LoginForm(navController: NavController, viewModel: LoginViewModel) {

    val context= LocalContext.current
    val scope= rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {

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
            isPassword = true,
            showPassword = viewModel.isShowPassword.value,
            onPasswordToggle = {
                viewModel.setPasswordVisibility(!viewModel.isShowPassword.value)
            },
            onValueChange = {
                viewModel.setPassword(it)
            },
            trailingIcon = Icons.Default.VisibilityOff
        )
        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
                if (isFormValid(viewModel, context)) {

                    val req = LoginRequest(
                        viewModel.email.value,
                        viewModel.password.value
                    )

                    scope.launch {
                        viewModel.login(req).collect {
                            when (it) {
                                is Resource.Error -> {
                                    viewModel.setLoadingState(false)
                                    showToast(context, it.message.toString())
                                }
                                is Resource.Loading -> viewModel.setLoadingState(true)
                                is Resource.Success -> {
                                    viewModel.setLoadingState(false)
                                    navController.popBackStack()
                                    navController.navigate(Route.HOME_SCREEN)
                                }
                            }
                        }
                    }
                }
            },
            type = ButtonType.LARGE,
            text = stringResource(
                R
                    .string.login
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(R.string.dont_have_account), style = Type.textxsRegular())
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.register),
                style = Type.textxsRegular(),
                color = Primary700,
                modifier = Modifier.clickable {
                    navController.navigate(Route.REGISTER_SCREEN)
                }
            )
        }
    }
}

fun isFormValid(viewModel: LoginViewModel, context: Context): Boolean {

    if (viewModel.email.value.isEmpty() || viewModel.password.value.isEmpty()) {
        showToast(context, context.getString(R.string.fill_all_the_field))
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

    return true
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}