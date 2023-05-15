package com.paulo.mymovie.data.repositories.network.impls

import com.paulo.mymovie.data.repositories.network.apis.Api
import com.paulo.mymovie.data.repositories.network.dtos.MovieDto
import com.paulo.mymovie.data.repositories.network.factory.FactoryMovies
import com.paulo.mymovie.data.repositories.network.util.ResultNetwork
import com.paulo.mymovie.domain.contracts.repositories.IRemoteRepositoryMovie
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.model.Video
import retrofit2.HttpException
import kotlin.jvm.Throws


class MovieNetworkImpl(
    private val api: Api
) : IRemoteRepositoryMovie {

    override suspend fun getAllMovies(factoryMovies: FactoryMovies): List<Movie> {

        try {
            /******************************
                Desging Pattern Factory Method, para retorno da api entre Trending e Popular
             *******************/
            val response = when (factoryMovies) {
                FactoryMovies.POPULAR -> {
                    api.getAllPopularMovies()
                }

                else -> {
                    api.getAllMovies()
                }
            }

            if (response.results.isNullOrEmpty()) {
                return emptyList()
            }

            //remover all title == NULL
            val clearList = response.results.filter { it.title != null }

            return clearList.map { it.toEntity() }

        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun getAllVideos(idVideo: String): List<Video> {
        try {
            val response = api.videos(idVideo)
            if (response.results.isNullOrEmpty()) {
                return emptyList()
            }
            val clearList = response.results.filter { it.name != null }

            return clearList.map { it.toEntity() }

        } catch (e: HttpException) {
            throw e
        }
    }


}