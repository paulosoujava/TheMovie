package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.data.repositories.local.impls.FakeLocalRepository
import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository
import com.paulo.mymovie.dumb.singleMovie
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoveFavoriteUseCaseTest {

    private lateinit var repository: ILocalRepository

    @Before
    fun setUp() {
        repository = FakeLocalRepository()
    }

    @Test
    fun save_favorite_movies() = runBlocking {
        val useCase = RemoveFavoriteUseCase(repository)
        useCase.movie(1L)

        val useCaseAll = GetAllFavoriteUseCase(repository)
        val movie = useCaseAll.allMovies().first().count()
        Assert.assertTrue(movie == 8)

    }

}

