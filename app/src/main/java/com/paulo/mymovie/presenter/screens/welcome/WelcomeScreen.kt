@file:OptIn(ExperimentalPagerApi::class)

package com.paulo.mymovie.presenter.screens.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.paulo.mymovie.data.repositories.local.welcome.ListWelcome
import com.paulo.mymovie.presenter.screens.welcome.components.Footer
import com.paulo.mymovie.presenter.screens.welcome.components.Screens



@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    var offsetY by remember { mutableStateOf(0f) }
    val pagerState = rememberPagerState(initialPage = 0)
    val item = ListWelcome.initList()

    Box(modifier = modifier) {
        /**************************************************************************************
         *HORIZONTAL SCREENS
         ***************************************************************************************/

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Screens(item, pagerState, offsetY)

        }
        /**************************************************************************************
         * FOOTER BUTTONS
         ***************************************************************************************/

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Footer(pagerState, item, navController, viewModel)

        }
    }
}


