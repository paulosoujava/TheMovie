package com.paulo.mymovie.data.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paulo.mymovie.domain.model.Movie

@Database(
    entities = [
        Movie::class
    ], version = 1, exportSchema = false
)
abstract class MyMovieDB : RoomDatabase() {
    abstract fun myMovieDao(): MyMovieDao
}