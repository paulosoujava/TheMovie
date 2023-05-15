package com.paulo.mymovie.domain.usecases.welcome

import com.paulo.mymovie.domain.contracts.repositories.IDataStoreRepository

class CompletedWelcomeGetUseCase(
private val repository: IDataStoreRepository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}