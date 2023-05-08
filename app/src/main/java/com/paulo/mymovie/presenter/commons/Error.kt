package com.paulo.mymovie.presenter.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.mymovie.R
import com.paulo.mymovie.presenter.theme.Orange

@Composable
fun ErrorMessage(message: String, onRefresh: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.error),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 200.dp),
                text = "Ops!!", style = TextStyle(
                    color = Color.White,
                    fontSize = 34.sp,

                    )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 50.dp, start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Center,
                text = message, style = TextStyle(
                    color = Color.White,
                    fontSize = 34.sp
                )
            )
            TextButton(
                onClick = onRefresh,
                border = BorderStroke(1.dp, Orange),
            ) {
                Text(
                    text = "Refresh", style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    ),

                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }


    }
}