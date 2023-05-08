package com.paulo.mymovie.presenter.screens.welcome.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.paulo.mymovie.domain.model.OnBoardingData
import com.paulo.mymovie.presenter.graph.Screen
import com.paulo.mymovie.presenter.screens.welcome.WelcomeViewModel



@OptIn(ExperimentalPagerApi::class)
@Composable
fun Footer(
    pagerState: PagerState,
    item: ArrayList<OnBoardingData>,
    navController: NavHostController,
    viewModel: WelcomeViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Black.copy(.5f),
        elevation = 0.dp,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 160.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = item.size,
                modifier = Modifier.padding(18.dp),
                activeColor = Color.Green,
                inactiveColor = Color.LightGray
            )
            Text(
                text = item[pagerState.currentPage].title,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 10.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = item[pagerState.currentPage].desc,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 30.dp),
                color = Color.White,
                fontSize = 21.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {


                if (pagerState.currentPage != 2) {
                    OutlinedButton(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevation(10.dp),
                        border = BorderStroke(2.dp, Color.White),
                        onClick = {
                            navigate(navController, viewModel)
                        },
                    ) {
                        Text(
                            text = "Pular tudo",
                            color = Color.LightGray,
                            textAlign = TextAlign.Right,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {

                    Button(
                        onClick = {
                            navigate(navController, viewModel)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF5D9410),
                        ),
                        contentPadding = PaddingValues(vertical = 12.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Text(
                            text = "Bora lá",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}


private fun navigate(navController: NavHostController, viewModel: WelcomeViewModel) {
    navController.popBackStack()
    navController.navigate(Screen.Home.route)
    viewModel.saveOnBoardingState(true)
}