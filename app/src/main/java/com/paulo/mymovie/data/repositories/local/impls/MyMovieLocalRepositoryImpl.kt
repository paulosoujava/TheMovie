package com.paulo.mymovie.data.repositories.local.impls

import com.paulo.mymovie.data.repositories.local.MyMovieDao
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.repositories.ILocalRepository
import kotlinx.coroutines.flow.Flow

class MyMovieLocalRepositoryImpl(
private val dao: MyMovieDao
) : ILocalRepository {

    /************************************************************
    // TRENDING
    *************************************************************/
    override suspend fun getMovies(): Flow<List<Movie>> {
        return dao.getFavorite()
    }

    override suspend fun getMovieByCode(code: Long): Flow<Movie?> {
        return dao.getFavoriteByCode(code = code)
    }

    override suspend fun addMovie(trending: Movie) {
        dao.addFavorite(trending = trending)
    }

    override suspend fun deleteMovie(code: Long) {
        dao.deleteFavorite(code = code)
    }


}