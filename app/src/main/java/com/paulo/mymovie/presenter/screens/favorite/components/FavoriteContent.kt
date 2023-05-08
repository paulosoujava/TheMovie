package com.paulo.mymovie.presenter.screens.favorite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.paulo.mymovie.presenter.commons.FavoriteViewModel
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.theme.BlackLight
import com.paulo.mymovie.presenter.util.pathImage

@Composable
fun FavoriteContent(
    list: List<Movie>,
    navController: NavHostController,
    viewModel: FavoriteViewModel
) {
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
            items(list) { movie ->
                Box {
                    Card(
                        modifier = Modifier
                            .clickable {
                                navigateToDetail(
                                    movie = movie,
                                    navController = navController,
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Favorite,
                            contentDescription = "",
                            tint = Color.Red,
                            modifier = Modifier.clickable {
                                viewModel.onRemove(movie)
                            }
                        )
                    }
                }

            }
        }
    )
}
