package com.syafi.skinscan.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.history.component.HistoryContent
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun HistoryScreen(navController: NavController, viewModel: HistoryViewModel= hiltViewModel()) {

    Column(
        Modifier
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

            Column(Modifier.padding(top = 55.dp)) {
                Text(
                    text = stringResource(id = R.string.history),
                    style = Type.displaysmSemiBold(),
                    color = Neutral50,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))

                CustomTextField(
                    text = "",
                    placeholder = stringResource(R.string.search_placeholder),
                    trailingIcon = Icons.Default.Search,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                )

                HistoryContent(navController, viewModel)
            }
        }
    }
}