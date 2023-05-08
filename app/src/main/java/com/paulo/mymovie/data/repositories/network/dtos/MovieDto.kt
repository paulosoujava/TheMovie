package com.paulo.mymovie.data.repositories.network.dtos

import com.google.gson.annotations.SerializedName
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.util.Constants

data class MovieDto(
    val adult: Boolean?,
    val id: Long?,
    val title: String?,
    val overview: String?,
    val popularity: Double?,
    val video: Boolean?,

    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Long?
) {
    fun toEntity() = Movie(
        id = id ?: 0,
        title = title ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        mediaType = mediaType ?: "",
        popularity = popularity ?: 0.0,
        releaseDate = releaseDate ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        backdropPath = "${backdropPath?.removePrefix("/")}",
        posterPath = "${posterPath?.removePrefix("/")}",
    )
}