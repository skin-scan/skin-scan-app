package com.syafi.skinscan.features.edit.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.edit.EditViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun ChangePassword(navController: NavController, viewModel: EditViewModel) {

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
                CustomTextField(text = "", trailingIcon = Icons.Default.VisibilityOff)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.new_password), style = Type.textsmMedium())
                CustomTextField(text = "", trailingIcon = Icons.Default.VisibilityOff)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.confirm_password), style = Type.textsmMedium())
                CustomTextField(text = "", trailingIcon = Icons.Default.VisibilityOff)
                Spacer(modifier = Modifier.height(32.dp))

                CustomButton(
                    onClick = {
                        viewModel.setDialogState(true)
                    },
                    type = ButtonType.LARGE,
                    text = stringResource(R.string.save)
                )
            }
        }
    }
}