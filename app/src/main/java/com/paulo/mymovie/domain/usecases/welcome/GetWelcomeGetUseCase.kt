package com.paulo.mymovie.domain.usecases.welcome

import com.paulo.mymovie.domain.contracts.repositories.IDataStoreRepository
import com.paulo.mymovie.presenter.graph.Screen
import kotlinx.coroutines.flow.first

class GetWelcomeGetUseCase(
private val repository: IDataStoreRepository
) {

    suspend operator fun invoke():Boolean {
        return repository.getOnBoardingState().first()
    }
}