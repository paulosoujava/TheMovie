package com.paulo.mymovie.presenter.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutState
import androidx.constraintlayout.compose.MotionScene
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.paulo.mymovie.R
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.commons.FavoriteViewModel
import com.paulo.mymovie.presenter.theme.Orange
import com.paulo.mymovie.presenter.util.pathImage

@Composable
@OptIn(ExperimentalMotionApi::class)
fun Content(
    motionScene: String,
    motionState: MotionLayoutState,
    movie: Movie,
    corners: Float,
    navController: NavHostController,
    showVideoTitles: MutableState<Boolean>,
    viewModelFavorite: FavoriteViewModel,
    state: State<Movie?>
) {
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        motionLayoutState = motionState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)

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
                .layoutId("headerImage"),
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White, shape = RoundedCornerShape(topStart = corners, topEnd = corners))
                .layoutId("contentBg")
        )

        Text(
            text = movie.title, fontSize = 32.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold, modifier = Modifier
                .layoutId("title")
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp, top = 30.dp)
        )

        Divider(
            Modifier
                .layoutId("titleDivider")
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        )

        Text(
            text = "Popularity : ${movie.popularity}", fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray, fontStyle = FontStyle.Italic,
            modifier = Modifier
                .layoutId("subTitle")
                .fillMaxWidth()
                .padding(6.dp)
        )

        Divider(
            Modifier
                .layoutId("subTitleDivider")
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        )

        Text(
            modifier = Modifier
                .layoutId("date")
                .fillMaxWidth()
                .padding(6.dp),
            text = "Release ${movie.releaseDate}", fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        val properties = motionProperties("actions")

        Row(
            modifier = Modifier
                .layoutId("actions")
                .background(properties.value.color("background")),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                    Text(text = "BACK", color = Color.White, fontSize = 12.sp)
                }
            }

            IconButton(onClick = {
                showVideoTitles.value = !showVideoTitles.value
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Outlined.PlayArrow, contentDescription = "", tint = Color.White)
                    Text(text = "VÍDEOS", color = Color.White, fontSize = 12.sp)
                }
            }
            IconButton(onClick = {
                viewModelFavorite.onSaveMovie(movie)
            }) {
                val color = if (state.value != null) Orange else Color.White
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        if (state.value != null) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "", tint = color
                    )
                    Text(text = "FAVORITE", color = color, fontSize = 12.sp)
                }
            }
        }

        Text(
            text = "Vote Average: ${movie.voteAverage}\n\n" +
                    "${movie.overview}",
            modifier = Modifier
                .fillMaxHeight()
                .layoutId("text")
                .padding(horizontal = 16.dp),
            fontSize = 18.sp,
        )

    }
}