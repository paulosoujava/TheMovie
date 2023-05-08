package com.paulo.mymovie.presenter.commons

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.paulo.mymovie.R
import kotlin.math.ceil
import kotlin.math.floor
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Green,
) {
    val filledStars = floor(rating).toInt()
    Log.e("CAL", "-- $filledStars")
    val unfilledStars = (stars - ceil(rating)).toInt()
    Log.e("CAL", "-- $unfilledStars")
    val halfStar = !(rating.rem(1).equals(0.0))
    Log.e("CAL", "-- $halfStar")
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon( painter = painterResource(id = R.drawable.baseline_full), contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_half),
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_regular),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

