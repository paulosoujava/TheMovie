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

class SaveFavoriteUseCaseTest{

    private lateinit var  repository: ILocalRepository

    @Before
    fun setUp() {
        repository = FakeLocalRepository()
    }

    @Test
    fun save_favorite_movies() = runBlocking{
        val useCase = SaveFavoriteUseCase(repository)
         useCase.movie(singleMovie)

        val useCaseSave = GetFavoriteUseCase(repository)
        val movie = useCaseSave.getMovie(1L).first()
        Assert.assertTrue(movie?.id == 1L)

    }

}

