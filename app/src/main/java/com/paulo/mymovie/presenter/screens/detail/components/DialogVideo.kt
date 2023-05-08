package com.paulo.mymovie.presenter.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.model.Video
import com.paulo.mymovie.presenter.commons.Video
import com.paulo.mymovie.presenter.screens.detail.DetailViewModel
import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage

@Composable
fun DialogVideo(
    movie: Movie,
    videoState: State<Video?>,
    viewModelDetail: DetailViewModel,
    showVideo: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
        //.background(Color.Black.copy(alpha = .9f))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.backdropPath.pathImage())
                .crossfade(true)
                .build(),
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .blur(10.dp)
                .fillMaxSize()

        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(10.dp),
                text = videoState.value?.name ?: viewModelDetail.list.first().name,
                color = Color.White,
                fontSize = 34.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                if (videoState.value?.key != null)
                    Video(videoId = videoState.value?.key!!)
                else
                    Video(videoId = viewModelDetail.list.first().key)
            }


            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        showVideo.value = !showVideo.value
                    },
                    modifier = Modifier
                        .border(1.dp, Orange, shape = RoundedCornerShape(10))
                        .background(Color.Black, shape = RoundedCornerShape(10))

                ) {
                    Text(text = "CLOSE", color = Color.Red, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}
