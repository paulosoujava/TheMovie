package com.paulo.mymovie.presenter.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulo.mymovie.core.BaseEvent
import com.paulo.mymovie.data.repositories.network.factory.FactoryMovies
import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.usecases.seeMore.MovieGetAllUseCase
import com.paulo.mymovie.domain.usecases.welcome.GetWelcomeGetUseCase
import com.paulo.mymovie.domain.util.handlerErrorsApi
import com.paulo.mymovie.presenter.graph.Screen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val useCase: GetWelcomeGetUseCase,
    private val useCaseMovies: MovieGetAllUseCase,
) : ViewModel() {

    //necessário fazer deste jeito para evitar o bug que pisca a tela anterior
    // passei o null qndo poderia passar direto a tela de welcome
    private val _startScreen: MutableState<String?> = mutableStateOf(null)
    val startScreen: State<String?> = _startScreen

    //STATE CONTENT TRENDING CARROUSEL
    private val _stateTrending = MutableStateFlow(MainUI())
    val stateTrending = _stateTrending

    //STATE CONTENT POPULAR
    private val _statePopular = MutableStateFlow(MainUI())
    val statePopular = _statePopular

    private val messageError = "Obtivemos um erro:"



    init {
        factoryData(FactoryMovies.POPULAR)
        factoryData(FactoryMovies.TRENDING)
        checkFlagWelcome()
    }



    private fun factoryData(factoryMovies: FactoryMovies) {
        viewModelScope.launch {
            val network = when (factoryMovies) {
                FactoryMovies.POPULAR -> useCaseMovies(FactoryMovies.POPULAR)
                FactoryMovies.TRENDING -> useCaseMovies(FactoryMovies.TRENDING)
            }
            when (network) {
                is NetworkResult.Error -> {
                    val messageFromErrors = handlerErrorsApi(network.code)
                    handleError(factoryMovies = factoryMovies, message = "$messageError\n$messageFromErrors\n [Cód:: ${network.code}]")
                }

                is NetworkResult.Exception -> {
                    handleError(factoryMovies = factoryMovies, message = "$messageError ${network.exception.message.toString()}")
                }

                is NetworkResult.Success -> {
                    success(network, factoryMovies = factoryMovies)
                }
            }
        }
    }

    private fun success(network: NetworkResult.Success<List<Movie>>, factoryMovies: FactoryMovies) {
        when (factoryMovies) {
            FactoryMovies.POPULAR ->
                _statePopular.update {
                    it.copy(
                        success = network.data,
                        event = BaseEvent.REGULAR,
                    )
                }

            else -> _stateTrending.update {
                it.copy(
                    success = network.data,
                    event = BaseEvent.REGULAR,
                )
            }
        }
    }

    private fun handleError(message: String, factoryMovies: FactoryMovies) {
        when (factoryMovies) {
            FactoryMovies.POPULAR -> _statePopular.update {
                it.copy(
                    errorMessage = message,
                    event = BaseEvent.ERROR
                )
            }

            else -> {
                _stateTrending.update {
                    it.copy(
                        errorMessage = message,
                        event = BaseEvent.ERROR
                    )
                }
            }

        }
    }

    private fun checkFlagWelcome() {
        viewModelScope.launch {
            if (useCase()) {
                _startScreen.value = Screen.Home.route
            } else {
                _startScreen.value = Screen.Welcome.route
            }
        }
    }
}