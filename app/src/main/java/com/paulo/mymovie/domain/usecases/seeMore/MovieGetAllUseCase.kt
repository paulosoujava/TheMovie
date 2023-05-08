package com.paulo.mymovie.domain.usecases.seeMore

import com.paulo.mymovie.data.repositories.network.factory.FactoryMovies
import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.repositories.IRemoteRepositoryMovie
import com.paulo.mymovie.domain.model.Movie
import retrofit2.HttpException

class MovieGetAllUseCase(
    private val repository: IRemoteRepositoryMovie
) {

    suspend operator fun invoke(): NetworkResult<List<Movie>> {
        return try {
            val result = repository.getAllMovies(FactoryMovies.TRENDING)
            NetworkResult.Success(result)
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message())
        } catch (t: Throwable) {
            NetworkResult.Exception(t)
        }
    }
}