package com.paulo.mymovie.presenter.commons

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.usecases.favorite.UseCasesFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: UseCasesFavorite
) : ViewModel() {

    private val _state = MutableStateFlow<Movie?>(null)
    val state: StateFlow<Movie?> = _state

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _list = mutableStateListOf<Movie>()
    val list: List<Movie> = _list


    fun onGetMovie(code: Long) {
        scope.launch {
            useCase.byCode.getMovie(code).collect {
                _state.value = it
            }
        }
    }


    fun onSaveMovie(trending: Movie) {
        scope.launch {
            if (_state.value != null)
                useCase.remove.movie(trending.id)
            else
                useCase.save.movie(trending)
        }
    }

    fun onRemove(movie: Movie) {
        scope.launch {
            useCase.remove.movie(movie.id)
            _list.remove(movie)
        }
    }

    suspend fun onGetAll() {
        scope.launch {
            useCase.get.allMovies().collect {
                it.forEach { movie ->
                    if (!_list.contains(movie))
                        _list.add(movie)
                }
            }
        }
        _isLoading.value = false
    }

}
