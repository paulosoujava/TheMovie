package com.paulo.mymovie.domain.repositories

import com.paulo.mymovie.data.repositories.network.factory.FactoryMovies
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.model.Video

interface IRemoteRepositoryMovie {
    suspend fun getAllMovies(factoryMovies: FactoryMovies): List<Movie>
    suspend fun getAllVideos(idVideo: String): List<Video>
}