package com.paulo.mymovie.data.repositories.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.util.Constants
import kotlinx.coroutines.flow.Flow


@Dao
interface MyMovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(trending: Movie)

    @Query("DELETE FROM ${Constants.TRENDING_DB} WHERE id = :code")
    fun deleteFavorite(code: Long)

    @Query("SELECT * FROM ${Constants.TRENDING_DB} ORDER BY id ASC")
    fun getFavorite():Flow<List<Movie>>

    @Query("SELECT * FROM ${Constants.TRENDING_DB}  WHERE id = :code")
    fun getFavoriteByCode(code: Long):Flow<Movie?>
}