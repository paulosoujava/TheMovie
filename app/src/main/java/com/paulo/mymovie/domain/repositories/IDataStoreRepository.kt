package com.paulo.mymovie.domain.repositories

import kotlinx.coroutines.flow.Flow

interface IDataStoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun getOnBoardingState(): Flow<Boolean>
}