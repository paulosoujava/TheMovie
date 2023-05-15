package com.paulo.mymovie.domain.usecases.favorite


import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository


class GetAllFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun allMovies() = repository.getMovies()
}

