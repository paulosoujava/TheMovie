package com.paulo.mymovie.data.repositories.local.impls

import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.dumb.list
import com.paulo.mymovie.dumb.listOfMovies
import com.paulo.mymovie.dumb.singleMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalRepository : ILocalRepository {
    override suspend fun getMovies(): Flow<List<Movie>> = flow {
        emit(listOfMovies())
    }

    override suspend fun getMovieByCode(code: Long): Flow<Movie?> = flow {
        emit( singleMovie )
    }

    override suspend fun addMovie(movie: Movie) {
        list.add(movie)
    }

    override suspend fun deleteMovie(code: Long) {
        list.removeIf { it.id == code }
    }

}
