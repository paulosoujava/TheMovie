package com.paulo.mymovie.domain.contracts.repositories

import com.paulo.mymovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    suspend fun getMovies():Flow<List<Movie>>
    suspend fun getMovieByCode(code: Long):Flow<Movie?>
    suspend fun addMovie(movie: Movie)
    suspend fun deleteMovie(code: Long)

}