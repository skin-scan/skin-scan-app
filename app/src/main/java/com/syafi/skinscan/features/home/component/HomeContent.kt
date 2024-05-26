package com.syafi.skinscan.features.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.PageIndicator
import com.syafi.skinscan.features.component.view.ScanResultCard
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type

@Composable
fun HomeContent(modifier: Modifier = Modifier) {

    val bannerList = listOf(
        R.drawable.home_banner,
        R.drawable.home_banner,
        R.drawable.home_banner,
    )

    val pagerState = rememberPagerState(pageCount = { bannerList.size })

    Column(modifier = modifier) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState, pageSize = PageSize.Fill) { index ->
                AsyncImage(
                    model = bannerList[index], contentDescription = "", modifier =
                    Modifier.clip(RoundedCornerShape(10.dp))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            PageIndicator(pagerState.currentPage, Primary700)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.last_scanning),
            style = Type.textmdBold(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            items(5) {
                ScanResultCard(
                    photo = R.drawable.place_holder,
                    timeStamp = "October 23, 2024",
                    name = "Upper Arm",
                    output = "Ringworm"
                )
            }
        }
        Spacer(modifier = Modifier.height(120.dp))
    }
}
