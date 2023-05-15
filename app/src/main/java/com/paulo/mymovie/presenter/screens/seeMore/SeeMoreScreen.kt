package com.paulo.mymovie.presenter.screens.seeMore

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.paulo.mymovie.presenter.screens.seeMore.components.CardSeeMoreItem
import com.paulo.mymovie.presenter.screens.seeMore.components.LoadingSeeMore
import com.paulo.mymovie.presenter.screens.seeMore.components.NavigationSeeMore
import com.paulo.mymovie.presenter.screens.seeMore.components.TabBarSeemore
import com.paulo.mymovie.presenter.theme.BlackLight

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeMoreScreen(navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val viewModel: SeeMoreViewModel = hiltViewModel()
    val search by viewModel.searchText.collectAsState()
    val movies by viewModel.movieList.collectAsState()
    val isSearching by viewModel.isSearch.collectAsState()



    Scaffold(
        containerColor = BlackLight,
        topBar = {
            TopAppBar(
                title = {
                    /**************************************************************************************
                     * TOP BAR
                     ***************************************************************************************/

                    TabBarSeemore(search, viewModel)
                },
                navigationIcon = {
                    /**************************************************************************************
                     * BACK ICON NAVIGATE
                     ***************************************************************************************/

                    NavigationSeeMore(navController)
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Black
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier
            .background(BlackLight)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        if (isSearching) {
            /**************************************************************************************
             * CONTENT LOADING
             ***************************************************************************************/

            LoadingSeeMore()
        } else
            LazyVerticalGrid(
                modifier = Modifier.background(BlackLight),
                columns = GridCells.Fixed(2),

                // content padding
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = 116.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    /**************************************************************************************
                     * CONTENT PAGE
                     ***************************************************************************************/

                    items(movies) { movie ->
                        CardSeeMoreItem(navController, movie)
                    }
                }
            )
    }
}


