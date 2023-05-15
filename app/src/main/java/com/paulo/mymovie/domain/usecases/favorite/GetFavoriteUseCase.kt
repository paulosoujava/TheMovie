package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository

class GetFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun getMovie(code:Long) = repository.getMovieByCode(code)

}