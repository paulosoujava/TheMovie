package com.paulo.mymovie.presenter.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.presenter.screens.detail.DetailScreen
import com.paulo.mymovie.presenter.screens.favorite.FavoriteScreen
import com.paulo.mymovie.presenter.screens.home.HomeScreen
import com.paulo.mymovie.presenter.screens.seeMore.SeeMoreScreen

import com.paulo.mymovie.presenter.screens.welcome.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Detail : Screen("detail")
    object SeeMore : Screen("seeMore")
    object Favorite : Screen("favorite")

}


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startScreen: String,
    contentHeaderHome: List<Movie>,
    contentHome: List<Movie>
) {
    NavHost(navController = navController, startDestination = startScreen) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                contentHeaderHome = contentHeaderHome,
                contentHome = contentHome
            )
        }
        composable(
            route = "${Screen.Detail.route}" +
                    "/{id}" +
                    "/{backdropPath}" +
                    "/{posterPath}" +
                    "/{title}" +
                    "/{voteAverage}" +
                    "/{voteCount}" +
                    "/{overview}" +
                    "/{releaseDate}" +
                    "/{popularity}" +
                    "/{originalLanguage}" +
                    "/{originalTitle}",
            arguments = listOf(
                navArgument("backdropPath") { type = NavType.StringType },
                navArgument("posterPath") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("voteAverage") { type = NavType.StringType },
                navArgument("voteCount") { type = NavType.StringType },
                navArgument("overview") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType },
                navArgument("releaseDate") { type = NavType.StringType },
                navArgument("popularity") { type = NavType.StringType },
                navArgument("originalLanguage") { type = NavType.StringType },
                navArgument("originalTitle") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->

            val backdropPath = navBackStackEntry.arguments?.getString("backdropPath")
            val posterPath = navBackStackEntry.arguments?.getString("posterPath")
            val title = navBackStackEntry.arguments?.getString("title")
            val voteAverage = navBackStackEntry.arguments?.getString("voteAverage")
            val voteCount = navBackStackEntry.arguments?.getString("voteCount")
            val overview = navBackStackEntry.arguments?.getString("overview")
            val id = navBackStackEntry.arguments?.getString("id")
            val releaseDate = navBackStackEntry.arguments?.getString("releaseDate")
            val popularity = navBackStackEntry.arguments?.getString("popularity")
            val originalLanguage = navBackStackEntry.arguments?.getString("originalLanguage")
            val originalTitle = navBackStackEntry.arguments?.getString("originalTitle")


            DetailScreen(
                navController = navController,
               movie = Movie(
                    backdropPath = backdropPath ?: "",
                    posterPath = posterPath?: "",
                    title = title?: "",
                    voteAverage = voteAverage?.toDouble()?: 0.0,
                    voteCount = voteCount?.toLong()?: 0,
                    overview = overview?: "",
                    id = id?.toLong()?: 0,
                    releaseDate = releaseDate?: "",
                    video = false,
                    popularity = popularity?.toDouble()?: 0.0,
                    originalLanguage = originalLanguage?: "",
                    originalTitle = originalTitle?: "",
                    mediaType = "movie"
                    )
            )
        }
        composable(route = Screen.SeeMore.route) {
            SeeMoreScreen(navController = navController)
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navController)
        }
    }
}