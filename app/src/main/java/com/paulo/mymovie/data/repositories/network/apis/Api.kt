package com.paulo.mymovie.data.repositories.network.apis

import com.paulo.mymovie.BuildConfig
import com.paulo.mymovie.data.repositories.network.dtos.Key
import com.paulo.mymovie.data.repositories.network.util.ResultNetwork
import com.paulo.mymovie.data.repositories.network.dtos.MovieDto
import com.paulo.mymovie.data.repositories.network.dtos.VideoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("trending/all/day?api_key=${BuildConfig.API_KEY}")
    suspend fun getAllMovies(): ResultNetwork<List<MovieDto>>


    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getAllPopularMovies(): ResultNetwork<List<MovieDto>>


    @GET("movie/{id}/videos?api_key=${BuildConfig.API_KEY}")
    suspend fun videos(
        @Path("id") id: String
    ): ResultNetwork<List<VideoDto>>


    @GET("/search/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun searchAMovie(): ResultNetwork<List<MovieDto>>


    @Headers(
        "accept: application/json",
        "Authorization: Bearer ${BuildConfig.TOKEN}"
    )
    @GET("/tv/changes?page=1")
    suspend fun getListOgKeys():ResultNetwork<Key>

    @Headers(
        "accept: application/json",
        "Authorization: Bearer ${BuildConfig.TOKEN}"
    )
    @GET("account/{id}/favorite/movies")
    suspend fun favoritesMovies(
        @Path("id") id: String
        ): ResultNetwork<List<MovieDto>>

    @Headers(
        "accept: application/json",
        "Authorization: Bearer ${BuildConfig.TOKEN}"
    )
    @GET("/account/{id}/favorite/tv?language=en-US")
    suspend fun tv(
        @Path("id") id: String
    ): ResultNetwork<List<MovieDto>>


}