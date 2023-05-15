package com.paulo.mymovie.presenter.screens.seeMore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulo.mymovie.domain.model.ListSeeMore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class SeeMoreViewModel: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearch = MutableStateFlow(false)
    val isSearch = _isSearch.asStateFlow()

    private val _movies = MutableStateFlow(ListSeeMore.movies)

    val movieList = searchText
        .debounce(600L)
        .onEach { _isSearch.update { _searchText.value.length>=3 } }
        .combine( _movies){text, movies ->
            if(text.isBlank()){
                movies
            }else{
                movies.filter {
                    it.matchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearch.update { false }  }
            .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _movies.value
        )



    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }
}