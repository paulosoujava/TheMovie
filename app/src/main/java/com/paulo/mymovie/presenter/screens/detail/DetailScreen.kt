package com.paulo.mymovie.presenter.screens.detail

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.model.Video
import com.paulo.mymovie.domain.util.isOnline
import com.paulo.mymovie.presenter.commons.FavoriteViewModel
import com.paulo.mymovie.presenter.commons.Video
import com.paulo.mymovie.presenter.screens.detail.components.Content
import com.paulo.mymovie.presenter.screens.detail.components.DialogVideo
import com.paulo.mymovie.presenter.screens.detail.components.ListTitleVideos

import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage


@OptIn(ExperimentalMotionApi::class,  ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(navController: NavHostController, movie: Movie) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    val motionState = rememberMotionLayoutState()
    val corners = 10f - ((motionState.currentProgress * 10)).coerceAtMost(10f)

    val viewModelFavorite: FavoriteViewModel = hiltViewModel()
    val viewModelDetail: DetailViewModel = hiltViewModel()


    val state = viewModelFavorite.state.collectAsState()
    val videoState = viewModelDetail.video.collectAsState()


    viewModelFavorite.onGetMovie(movie.id)

    val showVideo = remember {
        mutableStateOf(false)
    }
    val showVideoTitles = remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = Unit) {
        viewModelDetail.getAllVideos(movie.id.toString())
    }



    Box {
        /**************************************************************************************
         *CONTENT DETAIL
         ***************************************************************************************/

        Content(
            motionScene, motionState,
            movie, corners,
            navController,
            showVideoTitles,
            viewModelFavorite, state)

        /**************************************************************************************
         * DIALOG VIDEO
         ***************************************************************************************/
        AnimatedVisibility(
            visible = showVideo.value && isOnline(context),
            enter = expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            DialogVideo(movie, videoState, viewModelDetail, showVideo)
        }
        /**************************************************************************************
         * LIST TITLE VIDEO
         ***************************************************************************************/

        AnimatedVisibility(
            visible = showVideoTitles.value,
            enter = expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            ListTitleVideos(
                showVideoTitles,
                viewModelDetail,
                showVideo,
                movie.backdropPath.pathImage()
                )
        }
    }
}




