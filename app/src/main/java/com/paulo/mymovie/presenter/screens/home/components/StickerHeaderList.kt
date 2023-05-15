package com.paulo.mymovie.presenter.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.paulo.mymovie.R
import com.paulo.mymovie.presenter.graph.Screen


@Composable
fun stickHeaderList(section: Boolean, navController: NavHostController) {
    Row(
        modifier = Modifier
            .background(
                Color.Black,
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
            )
            .fillMaxWidth()
            .padding(bottom = 10.dp, top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${if(section) "The Most " else "The Least" } Voted",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(2f)
                .padding(15.dp)
        )
        TextButton(
            onClick = {

                navController.navigate(Screen.SeeMore.route)
            },
        ) {
            Text(text = "Ver todos", color = Color.LightGray.copy(alpha = .6f), fontSize = 12.sp)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
            tint = Color.LightGray.copy(alpha = .6f),
        )
    }
}


