package com.syafi.skinscan.features.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.syafi.skinscan.R
import com.syafi.skinscan.data.remote.response.detection.Detection
import com.syafi.skinscan.features.component.view.PageIndicator
import com.syafi.skinscan.features.component.view.ScanResultCard
import com.syafi.skinscan.ui.theme.Primary700
import com.syafi.skinscan.ui.theme.Type
import com.syafi.skinscan.util.Route
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    detectionList: List<Detection>
) {

    val bannerList = listOf(
        R.drawable.img_banner1,
        R.drawable.img_banner2,
        R.drawable.img_banner3
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val pagerState = rememberPagerState(pageCount = { bannerList.size })

    Column(modifier = modifier) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState, pageSize = PageSize.Fill) { index ->
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = bannerList[index], contentDescription = "", modifier =
                        Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(300.dp)
                            .height(136.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            PageIndicator(pagerState.currentPage, Primary700)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.last_scanning),
                style = Type.textmdBold(),
                textAlign = TextAlign.Start
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                navController.navigate(Route.HISTORY_SCREEN)
            },
            ) {
                Text(
                    text = stringResource(R.string.see_all),
                    color = Primary700,
                    style = Type.textsmRegular()
                )

                IconButton(
                    onClick = { navController.navigate(Route.HISTORY_SCREEN) },
                    modifier = Modifier.size(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "",
                        tint = Primary700,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(Modifier.fillMaxSize()) {
            items(detectionList) {
                ScanResultCard(
                    photo = it.image,
                    timeStamp = it.createdAt,
                    title = it.title,
                    medicalName = it.medicalName,
                    status= it.status,
                    onClick = {
                        navController.navigate(Route.RESULT_DETAIL(
                            id = it.id,
                            previousScreen = currentRoute.toString()
                        ))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(120.dp))
    }
}