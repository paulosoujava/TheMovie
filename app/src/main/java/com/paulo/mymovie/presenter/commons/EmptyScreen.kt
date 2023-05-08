package com.paulo.mymovie.presenter.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.mymovie.R

@Composable
fun EmptyScreen() {
    Image(
        painter = painterResource(id = R.drawable.empty),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(180.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "No favorite movies",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 180.dp)
        )
    }
}