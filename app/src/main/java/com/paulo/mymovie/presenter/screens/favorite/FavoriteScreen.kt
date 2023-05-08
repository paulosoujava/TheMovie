package com.paulo.mymovie.presenter.screens.favorite

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.commons.EmptyScreen
import com.paulo.mymovie.presenter.commons.FavoriteViewModel
import com.paulo.mymovie.presenter.graph.Screen
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.screens.favorite.components.FavoriteContent
import com.paulo.mymovie.presenter.screens.favorite.components.FavoriteLoading
import com.paulo.mymovie.presenter.screens.favorite.components.FavoriteTopBAr
import com.paulo.mymovie.presenter.theme.BlackLight
import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val viewModel: FavoriteViewModel = hiltViewModel()
    val loading = viewModel.isLoading.collectAsState()

    SideEffect {
        scope.launch {
            viewModel.onGetAll()
        }
    }
    val list = viewModel.list

    Scaffold(
        contentColor = BlackLight,
        containerColor = BlackLight,
        topBar = {
            /**************************************************************************************
             *TOP BAR
             ***************************************************************************************/

            FavoriteTopBAr(navController, scrollBehavior)
        },
        modifier = Modifier
            .background(BlackLight)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        /**************************************************************************************
         * LOADING
         ***************************************************************************************/

        AnimatedVisibility(visible = loading.value) {
            FavoriteLoading()
        }
        /**************************************************************************************
         * EMPTY SCREEN
         ***************************************************************************************/

        AnimatedVisibility(visible = list.isEmpty() && !loading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EmptyScreen()
            }
        }
        /**************************************************************************************
         * CONTENT
         ***************************************************************************************/

        FavoriteContent(list, navController, viewModel)
    }
}


