package com.paulo.mymovie.presenter.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.paulo.mymovie.domain.model.ListSeeMore
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.graph.Screen
import com.paulo.mymovie.presenter.screens.home.components.Header
import com.paulo.mymovie.presenter.screens.home.components.contentList
import com.paulo.mymovie.presenter.screens.home.components.stickHeaderList
import com.paulo.mymovie.presenter.theme.BlackLight
import com.paulo.mymovie.presenter.theme.Orange

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    contentHeaderHome: List<Movie>,
    contentHome: List<Movie>
) {

    val pagerState = rememberPagerState(initialPage = 0)

    //SINGLETON
    ListSeeMore.movies = contentHome

    val movList = contentHome.groupBy { it.voteAverage > 7 }

    Scaffold(
        /**************************************************************************************
         * FLOAT BUTTON
         ***************************************************************************************/

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 30.dp),
                onClick = {
                    navController.navigate(Screen.Favorite.route)
                },
                backgroundColor = Orange
            ) {
                Icon(Icons.Outlined.Favorite, tint = BlackLight, contentDescription = "")
            }
        }
    )
    {
        LazyColumn(
            modifier = Modifier.background(Color.Black)
        ) {
            /**************************************************************************************
             * HEADER VIEW
             ***************************************************************************************/

            item {
                Header(
                    contentHeaderHome = contentHeaderHome,
                    pagerState = pagerState,
                    navController = navController
                )
            }
            /**************************************************************************************
             * STICKER HEADER BY VOTE  > 7
             ***************************************************************************************/

            movList.forEach { (section, items) ->
                stickyHeader {
                    stickHeaderList(section, navController)

                }
                /**************************************************************************************
                 *CONTENT  STICKER HEADER
                 ***************************************************************************************/

                item {
                    contentList(section, items, navController)
                }

            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}

