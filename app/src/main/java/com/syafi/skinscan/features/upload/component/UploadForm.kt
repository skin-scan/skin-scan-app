package com.syafi.skinscan.features.upload.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.upload.UploadViewModel
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route

@Composable
fun UploadForm(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: UploadViewModel
) {
    Column(modifier) {
        Text(text = stringResource(R.string.upload_title), style = Type.textmdRegular())
        Spacer(modifier = Modifier.height(20.dp))

        ImagePreview(modifier = Modifier.fillMaxWidth(), navController, viewModel)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(R.string.photo_label), style = Type.textsmMedium())
        CustomTextField(text = "")
        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(
            onClick = {
                navController.navigate(Route.RESULT_DETAIL(id = "1"))
            },
            type = ButtonType.LARGE,
            text = stringResource(R.string.upload)
        )
    }
}