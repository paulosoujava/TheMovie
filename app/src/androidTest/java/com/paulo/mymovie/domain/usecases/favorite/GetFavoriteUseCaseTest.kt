package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.data.repositories.local.impls.FakeLocalRepository
import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetFavoriteUseCaseTest{

    private lateinit var  repository: ILocalRepository

    @Before
    fun setUp() {
        repository = FakeLocalRepository()
    }

    @Test
    fun get_favorite_movies() = runBlocking{
        val useCase = GetFavoriteUseCase(repository)
        val movie = useCase.getMovie(1L)
        Assert.assertTrue(movie.first()?.id == 1L)

    }

}

