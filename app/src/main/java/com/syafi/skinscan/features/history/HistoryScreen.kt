package com.syafi.skinscan.features.history

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.detection.Detection
import com.syafi.skinscan.features.component.view.CustomTextField
import com.syafi.skinscan.features.history.component.HistoryContent
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Constant
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(navController: NavController, viewModel: HistoryViewModel = hiltViewModel()) {

    val token by viewModel.token
    val detectionList by viewModel.detectionList
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.currTabIndex.value) {
        viewModel.getUserToken()

        token?.let {
            val bearerToken = "Bearer $it"

            when (viewModel.currTabIndex.value) {
                0 -> {
                    getDetectionResult(
                        viewModel, bearerToken, context, this,
                        query = null,
                        limit = null,
                        order = Constant.ASC,
                        status = null,
                        page = null
                    )
                }

                1 -> {
                    getDetectionResult(
                        viewModel, bearerToken, context, this,
                        query = null,
                        limit = null,
                        order = Constant.ASC,
                        status = Constant.DIAGNOSED.uppercase(),
                        page = null
                    )
                }

                2 -> {
                    getDetectionResult(
                        viewModel, bearerToken, context, this,
                        query = null,
                        limit = null,
                        order = Constant.ASC,
                        status = Constant.SAFE.uppercase(),
                        page = null
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel.searchQuery.value) {
        viewModel.getUserToken()

        token?.let {
            val bearerToken = "Bearer $it"

            getDetectionResult(
                viewModel, bearerToken, context, this,
                query = viewModel.searchQuery.value,
                limit = null,
                order = Constant.ASC,
                status = null,
                page = null
            )
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Primary700)
            .padding(bottom = 120.dp)
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
                    text = viewModel.searchQuery.value,
                    placeholder = stringResource(R.string.search_placeholder),
                    trailingIcon = Icons.Default.Search,
                    onValueChange = {
                        viewModel.setSearchQuery(it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                )

                HistoryContent(navController, viewModel, detectionList)
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun getDetectionResult(
    viewModel: HistoryViewModel,
    token: String,
    context: Context,
    scope: CoroutineScope,
    query: String?,
    limit: Int?,
    order: String?,
    status: String?,
    page: Int?
) {
    scope.launch {
        viewModel.getDetectionResult(
            token = token,
            query = query,
            limit = limit,
            order = order,
            status = status,
            page = page
        ).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(context, it.message.toString())
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setDetectionList(it.data?.data as List<Detection>)
                }
            }
        }
    }
}