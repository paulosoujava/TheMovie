package com.paulo.mymovie.data.repositories.local.impls

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.paulo.mymovie.data.repositories.local.MyMovieDB
import com.paulo.mymovie.data.repositories.local.MyMovieDao
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.dumb.singleMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MyMovieLocalRepositoryImplTest {

    private lateinit var dao: MyMovieDao
    private lateinit var database: MyMovieDB


    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyMovieDB::class.java
        ).allowMainThreadQueries().build()

        dao = database.myMovieDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insert_a_single_item_movie_in_db() = runBlocking {
        dao.addFavorite(singleMovie)
        val result = dao.getFavoriteByCode(1L)
        val movie = result.first()
        assert(movie?.title == "Test Room")
    }

    @Test
    fun get_a_movie_by_code() = runBlocking {
        dao.addFavorite(singleMovie)
        val result = dao.getFavoriteByCode(1L)
        val movie = result.first()
        Assert.assertTrue(movie != null)
    }

    @Test
    fun get_all_movie() = runBlocking {
        dao.addFavorite(singleMovie)
        val result = dao.getFavorite()
        Assert.assertTrue(result.first().count() == 1)
    }

    @Test
    fun delete_movies() = runBlocking {
        dao.addFavorite(singleMovie)
        dao.deleteFavorite(singleMovie.id)
        val result = dao.getFavorite()
        Assert.assertTrue(result.first().isNullOrEmpty())
    }

}