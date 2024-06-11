package com.syafi.skinscan.features.upload

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.upload.component.UploadForm
import com.syafi.skinscan.ui.theme.Neutral100
import com.syafi.skinscan.ui.theme.Neutral200
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.delay

@Composable
fun UploadScreen(
    navController: NavHostController,
    photoUri: String?,
    viewModel: UploadViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        if (photoUri == null) {
            Toast.makeText(
                context,
                context.getString(R.string.please_select_a_photo_first),
                Toast.LENGTH_SHORT
            ).show()
            delay(1500)
            navController.navigate(Route.ANALYZE_SCREEN)
        } else {
            viewModel.setPhotoUri(photoUri)
        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Primary700)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.img_buble,
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
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp), verticalAlignment = Alignment
                        .CenterVertically
                ) {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(Route.ANALYZE_SCREEN)
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
                        text = stringResource(R.string.upload_image),
                        style = Type.displaysmSemiBold(),
                        color = Neutral50,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }

                UploadForm(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Neutral100,
                            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    navController,
                    viewModel
                )
            }
        }
    }
}