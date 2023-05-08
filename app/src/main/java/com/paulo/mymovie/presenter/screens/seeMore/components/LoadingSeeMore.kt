package com.paulo.mymovie.presenter.screens.seeMore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.paulo.mymovie.presenter.theme.Orange

@Composable
 fun LoadingSeeMore() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2B2B)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            color = Orange,
            strokeWidth = 1.dp
        )
    }
}
