package com.paulo.mymovie.dumb

import com.paulo.mymovie.domain.model.Movie

val singleMovie = Movie(
    1L,
    "",
    "Test Room",
    "",
    "",
    "",
    "",
    "",
    0.0,
    "",
    false,
    0.0,
    0
)

val list = mutableListOf<Movie>()

fun listOfMovies(): List<Movie> {

    repeat(10) {
        list.add(
            Movie(
                it.toLong(),
                "",
                "Test Room $it",
                "",
                "",
                "",
                "",
                "",
                0.0,
                "",
                false,
                0.0,
                0
            )
        )
    }
    return list
}