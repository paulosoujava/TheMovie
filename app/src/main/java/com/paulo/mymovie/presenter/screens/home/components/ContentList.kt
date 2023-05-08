package com.paulo.mymovie.presenter.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage

@Composable
fun contentList(items: List<Movie>, navController: NavHostController) {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        repeat(items.size) {
            Card(
                modifier = Modifier
                    .clickable {
                       navigateToDetail(items[it], navController)
                    }
                    .padding(10.dp)
                    .size(180.dp, 280.dp),
                contentColor = Color.White,
                elevation = 12.dp,
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(modifier =  Modifier.size(280.dp, 300.dp)){

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(items[it].posterPath.pathImage())
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.logo),
                        contentDescription = items[it].title,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(280.dp, 300.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                    )
                    Row(Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text =  String.format("%.1f",items[it].voteAverage),
                            color = Color.Black,
                            modifier = Modifier.background(
                                color = Orange,
                                shape = RoundedCornerShape(20.dp),
                            ).padding(9.dp)
                        )
                    }
                    Row (Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = items[it].title,
                            maxLines = 2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black.copy(alpha = .7f)).padding(10.dp)
                        )
                    }
                }

            }
        }
    }
}


