package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.domain.repositories.ILocalRepository

class GetAllFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun allMovies() = repository.getMovies()

}