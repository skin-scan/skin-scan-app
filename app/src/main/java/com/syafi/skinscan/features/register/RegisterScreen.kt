package com.syafi.skinscan.features.register

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.SuccessPopup
import com.syafi.skinscan.features.register.component.RegisterForm
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Neutral700
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary700)
    ) {

        if (viewModel.isShowDialog.value) {
            SuccessPopup(
                onButtonClick = {
                    viewModel.setDialogState(false)
                    navController.popBackStack()
                    navController.navigate(Route.LOGIN_SCREEN)
                },
                onDismiss = {
                    viewModel.setDialogState(false)
                },
                message = stringResource(id = R.string.success_congrats)
            )
        }

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
                        navController.navigate(Route.LOGIN_SCREEN)
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
                    text = stringResource(id = R.string.register),
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
                    text = stringResource(R.string.welcome),
                    color = Primary900,
                    style = Type.textlgSemiBold()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.register_greeting),
                    color = Neutral700,
                    style = Type.textxsRegular()
                )
                Spacer(modifier = Modifier.height(30.dp))

                RegisterForm(navController = navController, viewModel)
            }
        }
    }

}