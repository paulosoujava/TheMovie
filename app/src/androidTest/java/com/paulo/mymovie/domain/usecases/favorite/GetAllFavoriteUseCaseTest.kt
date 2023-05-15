package com.paulo.mymovie.domain.usecases.favorite

import com.paulo.mymovie.data.repositories.local.impls.FakeLocalRepository
import com.paulo.mymovie.domain.contracts.repositories.ILocalRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllFavoriteUseCaseTest{

    private lateinit var  repository: ILocalRepository

    @Before
    fun setUp() {
        repository = FakeLocalRepository()
    }

    @Test
    fun get_all_favorite_movies() = runBlocking{
        val useCase = GetAllFavoriteUseCase(repository)
        val count = useCase.allMovies().first().count()
        Assert.assertTrue(count > 0)
    }

}

