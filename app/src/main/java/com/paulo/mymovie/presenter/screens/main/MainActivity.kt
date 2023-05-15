package com.paulo.mymovie.presenter.screens.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.paulo.mymovie.core.BaseEvent
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.commons.ErrorMessage
import com.paulo.mymovie.presenter.commons.Loading
import com.paulo.mymovie.presenter.graph.SetupNavGraph
import com.paulo.mymovie.presenter.graph.navigateToDetail
import com.paulo.mymovie.presenter.theme.MyMovieTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val stateTrending = splashViewModel.stateTrending.collectAsState()
            val statePopular = splashViewModel.statePopular.collectAsState()
            val showLoading = remember {
                mutableStateOf(true)
            }
            installSplashScreen().setKeepOnScreenCondition {
                stateTrending.value.event != BaseEvent.LOADING &&
                        statePopular.value.event != BaseEvent.LOADING
            }
            val context = LocalContext.current

            MyMovieTheme {
                /**************************************************************************************
                 * REMOVE SYSTEM BARS
                 ***************************************************************************************/
                WindowCompat.setDecorFitsSystemWindows(window, false)
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Color.Transparent)
                systemUiController.setNavigationBarColor(Color.Transparent)

                val screen by splashViewModel.startScreen
                val navController = rememberNavController()

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    /**************************************************************************************
                     * FIRST LOADING
                     ***************************************************************************************/

                    AnimatedVisibility(visible = showLoading.value) {
                        Loading()
                    }


                    when (stateTrending.value.event) {
                        /**************************************************************************************
                         * REGULAR STATE
                         ***************************************************************************************/

                        BaseEvent.REGULAR -> {
                            AnimatedVisibility(visible = screen != null) {
                                SetupNavGraph(
                                    navController = navController,
                                    startScreen = screen!!,
                                    contentHeaderHome = stateTrending.value.success,
                                    contentHome = statePopular.value.success,
                                )

                            }
                            showLoading.value = false
                        }
                        /**************************************************************************************
                         * STATE LOADING
                         ***************************************************************************************/


                        BaseEvent.LOADING -> {
                            AnimatedVisibility(visible = showLoading.value) {
                                Box(
                                    modifier = Modifier
                                        .background(Color.Black)
                                        .fillMaxSize(), contentAlignment = Alignment.Center
                                ) {
                                    Loading()
                                }
                            }
                        }
                        /**************************************************************************************
                         * ERROR STATE
                         ***************************************************************************************/

                        BaseEvent.ERROR -> {
                            ErrorMessage(message = stateTrending.value.errorMessage) {
                                Toast.makeText(context, "Not implemented.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

    }
}


