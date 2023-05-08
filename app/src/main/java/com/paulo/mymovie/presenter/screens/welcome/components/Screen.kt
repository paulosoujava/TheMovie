package com.paulo.mymovie.presenter.screens.welcome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.paulo.mymovie.domain.model.OnBoardingData
import kotlin.math.absoluteValue

@Composable
@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
fun Screens(item: ArrayList<OnBoardingData>, pagerState: PagerState, offsetY: Float) {
    var offsetY1 = offsetY
    HorizontalPager(
        count = item.size,
        state = pagerState,
        modifier = Modifier
            .pointerInteropFilter {
                offsetY1 = it.y
                false
            }
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val pageOffset = pagerState.currentPageOffset
                    translationX = size.width * pageOffset

                    val endOffset = pagerState.currentPageOffset

                    shadowElevation = 10f
                    /* shape = CirclePath(
                                progress = 1f - endOffset.absoluteValue,
                                origin = Offset(
                                    size.width,
                                    offsetY,
                                )
                            )*/
                    clip = true

                    val absoluteOffset = pagerState.currentPageOffset.absoluteValue
                    val scale = 1f + (absoluteOffset.absoluteValue * .4f)

                    scaleX = scale
                    scaleY = scale

                    val startOffset = pagerState.currentPageOffset
                    alpha = (2f - startOffset) / 2f

                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = item[page].image),
                contentDescription = item[page].title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
