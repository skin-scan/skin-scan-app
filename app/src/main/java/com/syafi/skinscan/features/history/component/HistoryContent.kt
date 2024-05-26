package com.syafi.skinscan.features.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.ScanResultCard
import com.syafi.skinscan.features.history.HistoryViewModel
import com.syafi.skinscan.ui.theme.Neutral50
import com.syafi.skinscan.ui.theme.Neutral800
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Type

@Composable
fun HistoryContent(navController: NavController, viewModel: HistoryViewModel) {

    val tabTitle = listOf(
        stringResource(id = R.string.all),
        stringResource(id = R.string.diagnosed),
        stringResource(id = R.string.safe)
    )

    Column(
        Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Neutral50)
    ) {
        TabRow(
            selectedTabIndex = 1,
            modifier = Modifier.fillMaxWidth(),
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(it[viewModel.currTabIndex.value])
                        .padding(horizontal = 35.dp)
                        .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                    color = Primary700,
                    height = 4.dp
                )}
        ) {
            tabTitle.forEachIndexed { index, tab ->
                Tab(
                    selected = index == viewModel.currTabIndex.value,
                    onClick = {
                        viewModel.setIndex(index)
                        viewModel.setType(tab)
                    },
                    text = {
                        Text(
                            text = tab,
                            style =
                            if (index == viewModel.currTabIndex.value) Type.textsmSemiBold()
                            else Type.textsmMedium(),
                        )
                    }, selectedContentColor = Primary900,
                    unselectedContentColor = Neutral800
                )
            }
        }
        LazyVerticalGrid(columns = GridCells.Adaptive(200.dp)) {
            items(10) {
                ScanResultCard(
                    photo = R.drawable.place_holder,
                    timeStamp = "October 23, 2024",
                    name = "Upper Arm",
                    output = "Ringworm"
                )
            }
        }
    }
}