package com.paulo.mymovie.presenter.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage

@Composable
@OptIn(ExperimentalPagerApi::class)
 fun Header(
    contentHeaderHome: List<Movie>,
    pagerState: PagerState,
    navController: NavHostController
) {
    val newList = contentHeaderHome.filter { it.voteAverage >= 7}
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            count = newList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),
        ) { page ->
            val movie = newList[page]
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropPath.pathImage())
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.logo),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .blur(10.dp)
                    .fillMaxWidth()
                    .height(330.dp)

            )
            Row(
                modifier = Modifier
                    .background(
                        Color.Black.copy(alpha = 0.6f)
                    )
            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(180.dp, 300.dp),
                    contentColor = Color.White,
                    elevation = 12.dp,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.posterPath.pathImage())
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.logo),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(shape = RoundedCornerShape(20.dp)),
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 16.dp, end = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp, top = 20.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = Orange,
                                    shape = RoundedCornerShape(60)
                                )
                                .padding(5.dp),
                            text = String.format("%.1f", movie.voteAverage),
                            color = Color.Black,
                            fontSize = 12.sp,
                        )
                    }
                    /*RatingBar(
                                    rating = contentHome[page].voteAverage,
                                    modifier = Modifier
                                        .padding(end = 30.dp, top = 15.dp, bottom = 10.dp)
                                )*/
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(top = 12.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = movie.overview,
                        maxLines = 5,
                        modifier = Modifier.padding(top = 12.dp),
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Original language: " + movie.originalLanguage.uppercase(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(
                            onClick = {
                                navigateToDetail(movie, navController)
                            },
                        ) {
                            Text(text = "ver mais", color = Orange)
                        }
                    }
                }

            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = newList.size,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp),
            activeColor = Color.Green,
            inactiveColor = Color.LightGray
        )

    }
}
