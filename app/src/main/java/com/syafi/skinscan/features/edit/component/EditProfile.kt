package com.syafi.skinscan.features.edit.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun EditProfile(navController: NavController, viewModel: EditViewModel) {

    if (viewModel.isDialogOpen.value) {
        SuccessPopup(
            onButtonClick = {
                navController.popBackStack()
                navController.navigate(Route.PROFILE_SCREEN)
            },
            message = stringResource(R.string.successfully_edit_profile)
        )
    }

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
                model = R.drawable.profile_place_holder,
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-45).dp)
                    .shadow(6.dp, CircleShape)
                    .clickable {

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

                    }
            )

            Column(Modifier.padding(top = 55.dp, end = 20.dp, start = 20.dp)) {

                Text(text = stringResource(R.string.name), style = Type.textsmMedium())
                CustomTextField(text = "")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(R.string.email), style = Type.textsmMedium())
                CustomTextField(text = "")
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