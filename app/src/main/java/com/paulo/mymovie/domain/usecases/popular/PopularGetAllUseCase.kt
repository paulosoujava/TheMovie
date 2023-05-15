package com.paulo.mymovie.domain.usecases.popular

import com.paulo.mymovie.data.repositories.network.factory.FactoryMovies
import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.contracts.repositories.IRemoteRepositoryMovie
import com.paulo.mymovie.domain.model.Movie
import retrofit2.HttpException

class PopularGetAllUseCase(
    private val repository: IRemoteRepositoryMovie
) {

    suspend operator fun invoke(): NetworkResult<List<Movie>> {
        return try {
            val result = repository.getAllMovies(FactoryMovies.POPULAR)
            NetworkResult.Success(result)
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message())
        } catch (t: Throwable) {
            NetworkResult.Exception(t)
        }
    }
}