package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository

class RemoveFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun movie(code: Long) = repository.deleteMovie(code = code)

}