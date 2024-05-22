package com.syafi.skinscan.features.login.component

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
import com.syafi.skinscan.features.component.CustomButton
import com.syafi.skinscan.features.component.CustomTextField
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun LoginForm(navController: NavController) {

    Column(Modifier.fillMaxWidth()) {
        
        Text(text = stringResource(R.string.email), style= Type.textsmMedium())
        CustomTextField(text = "")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.password), style= Type.textsmMedium())
        CustomTextField(text = "", trailingIcon = Icons.Default.VisibilityOff)
        Spacer(modifier = Modifier.height(32.dp))
        
        CustomButton(
            onClick = {navController.navigate(Route.HOME_SCREEN) },
            type = ButtonType.LARGE,
            text = stringResource(R
            .string.login)
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