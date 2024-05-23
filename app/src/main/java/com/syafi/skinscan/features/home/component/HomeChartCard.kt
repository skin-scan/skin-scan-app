package com.syafi.skinscan.features.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.syafi.skinscan.R
import com.syafi.skinscan.features.home.HomeViewModel
import com.syafi.skinscan.ui.theme.Base50
import com.syafi.skinscan.ui.theme.Neutral900
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Primary900
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.ui.theme.Type

@Composable
fun HomeChartCard(viewModel: HomeViewModel, modifier: Modifier = Modifier) {

    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice(
                stringResource(R.string.diagnosed_problem),
                viewModel.problemDiagnosed.value,
                Secondary500
            ),
            PieChartData.Slice(
                stringResource(R.string.without_problem),
                viewModel.safeDiagnosed.value,
                Primary700
            ),
        ),
        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(
        strokeWidth = 50f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true
    )

    val totalScanned = (viewModel.safeDiagnosed.value + viewModel.problemDiagnosed.value).toInt()

    Row(
        modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(20.dp))
            .background(Base50, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box {
            DonutPieChart(
                modifier = Modifier
                    .padding(6.dp)
                    .sizeIn(maxHeight = 135.dp),
                donutChartData,
                donutChartConfig,
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = totalScanned.toString(),
                    style = Type.textlgSemiBold(),
                    color = Neutral900
                )
                Text(
                    text = stringResource(R.string.photos), color = Primary900, style = Type
                        .text2xsRegular()
                )
            }
        }
        ChartInfo(viewModel.problemDiagnosed.value.toInt(), viewModel.safeDiagnosed.value.toInt())
    }
}