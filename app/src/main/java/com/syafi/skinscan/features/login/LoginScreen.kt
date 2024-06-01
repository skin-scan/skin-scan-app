package com.syafi.skinscan.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.Loading
import com.syafi.skinscan.features.login.component.LoginForm
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Neutral700
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel= hiltViewModel()) {

    if (viewModel.isLoading.value) {
        Loading()
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
            Text(
                text = stringResource(id = R.string.login),
                color = Neutral50,
                style = Type.displayxsBold(),
                modifier = Modifier.padding(top = 55.dp, start = 20.dp)
            )
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
                    text = stringResource(R.string.login_greeting),
                    color = Neutral700,
                    style = Type.textxsRegular()
                )
                Spacer(modifier = Modifier.height(30.dp))

                LoginForm(navController, viewModel)
            }
        }
    }
}