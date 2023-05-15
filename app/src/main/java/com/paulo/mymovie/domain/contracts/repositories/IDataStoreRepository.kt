package com.paulo.mymovie.domain.contracts.repositories

import kotlinx.coroutines.flow.Flow

interface IDataStoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun getOnBoardingState(): Flow<Boolean>
}