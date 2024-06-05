package com.syafi.skinscan.features.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.syafi.skinscan.R
import com.syafi.skinscan.features.component.view.CustomButton
import com.syafi.skinscan.features.component.view.CustomOutlinedButton
import com.syafi.skinscan.features.component.view.PageIndicator
import com.syafi.skinscan.features.welcome.component.WelcomeContent
import com.syafi.skinscan.ui.theme.Secondary500
import com.syafi.skinscan.util.ButtonType
import com.syafi.skinscan.util.Route
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(pageCount = { welcomeScreenDataList.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
        ) { index ->
            WelcomeContent(
                index,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        PageIndicator(pagerState.currentPage, Secondary500)
        Spacer(modifier = Modifier.height(52.dp))


        CustomButton(
            onClick = {
                scope.launch {

                    if (pagerState.currentPage == welcomeScreenDataList.size - 1) {
                        viewModel.setUserSession(true)
                        navController.navigate(Route.LOGIN_SCREEN)
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            type = ButtonType.LARGE,
            text =
            if (pagerState.currentPage == welcomeScreenDataList.size - 1) {
                stringResource(id = R.string.login)
            } else {
                stringResource(id = R.string.next)
            }
        )
    }
}