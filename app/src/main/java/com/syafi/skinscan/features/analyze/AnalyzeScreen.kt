package com.syafi.skinscan.features.analyze

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.analyze.component.ChooseMediaPicker
import com.syafi.skinscan.features.analyze.component.GuidelineCard
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route
import com.syafi.skinscan.util.getImageUri


@Composable
fun AnalyzeScreen(
    navController: NavController,
    setFabOnClick: (() -> Unit) -> Unit,
    viewModel: AnalyzeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    setFabOnClick {
        viewModel.setDialogState(true)
    }

    LaunchedEffect(key1 = viewModel.photoUri.value) {

        if (viewModel.photoUri.value != null) {
            navController.navigate(Route.UPLOAD_SCREEN(viewModel.photoUri.value.toString()))
        }
    }

    val galleryIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.setPhotoUri(uri)
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.please_pick_a_picture),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    val cameraIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it) {

            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.please_pick_a_picture),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    fun startCamera() {
        viewModel.setPhotoUri(getImageUri(context))
        cameraIntent.launch(viewModel.photoUri.value)
    }

    if (viewModel.isDialogOpen.value) {
        ChooseMediaPicker(
            onDismiss = { viewModel.setDialogState(false) },
            onGalleryClick = {
                viewModel.setDialogState(false)
                galleryIntent.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            onCameraClick = {
                viewModel.setDialogState(false)
                startCamera()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary700)
    ) {

        Box(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.img_buble2,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .sizeIn(maxWidth = 160.dp, maxHeight = 160.dp)
                    .align(Alignment.TopEnd)

            )

            Column(
                Modifier.padding(top = 55.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.analyze),
                    style = Type.displaysmSemiBold(),
                    color = Neutral50,
                    modifier = Modifier.padding(start = 20.dp)
                )


                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Neutral50, RoundedCornerShape(30.dp))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = stringResource(R.string.read_guideline),
                        style = Type.textsmRegular()
                    )
                    GuidelineCard(
                        Modifier
                            .fillMaxWidth()
                            .background(Primary50, RoundedCornerShape(10.dp))
                    )
                }
            }
        }
    }
}