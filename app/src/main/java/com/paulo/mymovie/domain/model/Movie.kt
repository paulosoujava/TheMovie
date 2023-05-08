package com.paulo.mymovie.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paulo.mymovie.domain.util.Constants

@Entity(tableName = Constants.TRENDING_DB)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val backdropPath: String,
    val title: String,
    val originalLanguage: String,
    val posterPath: String,
    val originalTitle: String,
    val overview: String,
    val mediaType: String,
    val popularity: Double,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
) {
    fun matchSearchQuery(query: String): Boolean {
        val matchCombinations = listOf(
            "$title$originalTitle",
            "$overview",
        )
        return matchCombinations.any {
            it.contains(query, true)
        }
    }
}
