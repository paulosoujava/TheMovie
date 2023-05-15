package com.paulo.mymovie.presenter.screens.seeMore.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.mymovie.presenter.screens.seeMore.SeeMoreViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TabBarSeemore(search: String, viewModel: SeeMoreViewModel) {
    val showSearch = remember {
        mutableStateOf(false)
    }
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    AnimatedVisibility(visible = !showSearch.value) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "See More",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            IconButton(onClick = {
                keyboardController?.show()
                showSearch.value = !showSearch.value

            }) {
                Icon(Icons.Filled.Search, "", tint = Color.White)
            }
        }
    }
    AnimatedVisibility(visible = showSearch.value) {
        TextField(
            value = search,
            onValueChange = viewModel::onSearchTextChanged,
            placeholder = {
                Text(
                    "Search here...",
                    color = Color.Black,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .size(300.dp, 45.dp),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                IconButton(onClick = {
                    showSearch.value = !showSearch.value
                    keyboardController?.hide()
                }) {
                    Icon(Icons.Default.Close, "", tint = Color.Black)
                }
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

}