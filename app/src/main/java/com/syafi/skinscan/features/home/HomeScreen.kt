package com.syafi.skinscan.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.syafi.skinscan.features.home.component.HomeChartCard
import com.syafi.skinscan.features.home.component.HomeContent
import com.syafi.skinscan.features.home.component.HomeGreet
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral100
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Primary200
import com.syafi.skinscan.ui.theme.Primary700

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    LazyColumn(
        Modifier.fillMaxSize().background(Primary200),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ) {
        item {
            Box() {
                HomeGreet(
                    name = "Syafi",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(204.dp)
                        .background(
                            Primary700,
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        )
                        .padding(end = 32.dp)
                )

                HomeChartCard(
                    viewModel = viewModel,
                    Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 75.dp)
                )
            }
        }

        item {
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Neutral50, RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .padding(30.dp)
            )
        }
    }
}