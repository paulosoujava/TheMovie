package com.paulo.mymovie.presenter.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulo.mymovie.domain.usecases.welcome.CompletedWelcomeGetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCaseDataSource: CompletedWelcomeGetUseCase
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            useCaseDataSource(completed)
        }

    }
}