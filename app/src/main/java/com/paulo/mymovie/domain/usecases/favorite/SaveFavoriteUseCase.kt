package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.repositories.ILocalRepository

class SaveFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun movie(movie: Movie) = repository.addMovie(movie)

}