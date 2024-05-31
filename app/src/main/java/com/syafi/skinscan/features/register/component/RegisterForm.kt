package com.syafi.skinscan.features.register.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.register.RegisterViewModel
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun RegisterForm(navController: NavController, viewModel: RegisterViewModel) {

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
            text = viewModel.name.value,
            onValueChange = {
                viewModel.setEmail(it)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.confirm_password), style = Type.textsmMedium())
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

        Text(text = stringResource(R.string.password), style = Type.textsmMedium())
        CustomTextField(text = "", trailingIcon = Icons.Default.VisibilityOff)
        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
//                viewModel.setDialogState(true)
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