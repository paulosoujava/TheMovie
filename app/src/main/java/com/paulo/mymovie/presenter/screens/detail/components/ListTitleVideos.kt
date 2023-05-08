package com.paulo.mymovie.presenter.screens.detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.R
import com.paulo.mymovie.presenter.screens.detail.DetailViewModel
import com.paulo.mymovie.presenter.util.pathImage

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ListTitleVideos(
    showVideoTitles: MutableState<Boolean>,
    viewModelDetail: DetailViewModel,
    showVideo: MutableState<Boolean>,
    image: String
) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .blur(10.dp)
                .fillMaxSize()

        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black
                        )
                        .padding(top = 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select a video",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 30.dp, top = 30.dp),
                        fontSize = 21.sp, fontWeight = FontWeight.SemiBold
                    )
                    IconButton(onClick = {
                        showVideoTitles.value = !showVideoTitles.value
                    }) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                    }

                }

            }
            items(viewModelDetail.list) {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModelDetail.setIdVideo(it)
                            showVideoTitles.value = !showVideoTitles.value
                            showVideo.value = !showVideo.value
                        }
                        .padding(10.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(
                                topStartPercent = 60,
                                bottomEndPercent = 60
                            )
                        )
                        .padding(20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}