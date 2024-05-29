package com.syafi.skinscan.features.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.dialog.DeleteDialog
import com.syafi.skinscan.features.component.dialog.SuccessPopup
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary100
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun ResultDetail(
    navController: NavController,
    id: String = "",
    viewModel: ResultDetailViewModel = hiltViewModel(),
) {

    if (viewModel.isDeleteDialogOpen.value) {
        DeleteDialog(
            onDismiss = { viewModel.setDeleteDialogState(false) },
            onPositiveClick = {
                viewModel.setDeleteDialogState(false)
                viewModel.setSuccessDialogState(true)
            },
            onNegativeClick = {
                viewModel.setDeleteDialogState(false)
            }
        )
    }

    if (viewModel.isSuccessDialogOpen.value) {
        SuccessPopup(
            onButtonClick = {
                navController.popBackStack()
                navController.navigate(Route.HISTORY_SCREEN)
            },
            message = stringResource(R.string.delete_success)
        )
    }

    Box(Modifier.fillMaxSize()) {

        AsyncImage(
            model = R.drawable.detail_place_holder,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )

        IconButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(Route.HISTORY_SCREEN)
            },
            Modifier
                .padding(top = 55.dp, start = 20.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor = Neutral50)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "",
            )
        }

        Card(
            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Neutral50),
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .align(Alignment.BottomCenter)
                .offset(y = 60.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Primary100),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Left Hand",
                    style = Type.textmdSemiBold(),
                    color = Primary700,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Neutral50)
                        .padding(20.dp),
                ) {
                    Text(text = stringResource(R.string.result), style = Type.textsmSemiBold())
                    Text(text = "Psoriasis", style = Type.textsmRegular())
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.risk_assessment), style = Type
                            .textsmSemiBold()
                    )
                    Text(
                        text = "Psoriasis is a chronic autoimmune condition causing red, scaly patches on the skin. Treatments like creams and light therapy can help, but prevention involves good skin care, avoiding triggers, and seeking medical advice for worsening symptoms. Regular dermatologist check-ups are important for effective management.",
                        style = Type.textsmRegular()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.time_detection),
                        style = Type.textsmSemiBold()
                    )
                    Text(text = "October. 29 2024. 01:00 AM", style = Type.textsmRegular())
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            CustomButton(
                onClick = { viewModel.setDeleteDialogState(true) },
                type = ButtonType.LARGE,
                text = stringResource(R.string.delete),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}