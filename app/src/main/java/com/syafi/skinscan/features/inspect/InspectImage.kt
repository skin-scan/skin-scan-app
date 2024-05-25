package com.syafi.skinscan.features.inspect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.util.Route

@Composable
fun InspectImage(navController: NavController, photoUri: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Neutral900),
        verticalArrangement = Arrangement.spacedBy(60.dp)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(Route.UPLOAD_SCREEN(photoUri))
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

        AsyncImage(model = photoUri, contentDescription = "", Modifier.fillMaxWidth())
    }
}