package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.domain.repositories.ILocalRepository

class GetFavoriteUseCase(
    private val repository: ILocalRepository
) {
     suspend fun getMovie(code:Long) = repository.getMovieByCode(code)

}