package com.paulo.mymovie.presenter.graph

import androidx.navigation.NavHostController
import com.paulo.mymovie.domain.model.Movie

fun navigateToDetail(movie: Movie, navController: NavHostController) {
    navController.navigate(
        Screen.Detail.route + "/${movie.id}" +
                "/${movie.backdropPath}" +
                "/${movie.posterPath}" +
                "/${movie.title}" +
                "/${movie.voteAverage}" +
                "/${movie.voteCount}" +
                "/${movie.overview}" +
                "/${movie.releaseDate}" +
                "/${movie.popularity}" +
                "/${movie.originalLanguage}" +
                "/${movie.originalTitle}"
    )
}
