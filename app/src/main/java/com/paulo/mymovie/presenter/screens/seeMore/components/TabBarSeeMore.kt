package com.paulo.mymovie.presenter.screens.seeMore.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.mymovie.presenter.screens.seeMore.SeeMoreViewModel


@Composable
fun TabBarSeemore(search: String, viewModel: SeeMoreViewModel) {
    TextField(
        value = search,
        onValueChange = viewModel::onSearchTextChanged,
        modifier = Modifier

            .size(300.dp, 45.dp),
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(Icons.Filled.Search, "", tint = Color.Black)
        },
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            )
    )
}