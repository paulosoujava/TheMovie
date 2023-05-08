package com.paulo.mymovie.presenter.screens.seeMore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.graph.Screen
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.util.pathImage


@Composable
fun CardSeeMoreItem(navController: NavHostController, movie: Movie) {
    Card(
        modifier = Modifier
            .clickable {
                navigateToDetail(
                    navController = navController,
                    movie = movie
                )
            }
            .padding(10.dp)
            .size(180.dp, 280.dp),
        contentColor = Color.White,
        elevation = 12.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPath.pathImage())
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_full),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))
        )

    }
}