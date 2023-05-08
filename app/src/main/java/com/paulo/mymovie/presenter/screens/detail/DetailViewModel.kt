package com.paulo.mymovie.presenter.screens.detail

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.model.Video
import com.paulo.mymovie.domain.usecases.video.VideoGetAllUseCase
import com.paulo.mymovie.domain.util.handlerErrorsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: VideoGetAllUseCase
) : ViewModel() {


    private val _list = mutableStateListOf<Video>()
    val list: List<Video> = _list
    private val messageError = "Obtivemos um erro:"

    private val _video = MutableStateFlow<Video?>(null)
    val video = _video

    fun setIdVideo(video: Video) {
        _video.value = video

    }

    fun getAllVideos(idVideo: String) {
        viewModelScope.launch {
            when (val network = useCase(idVideo)) {
                is NetworkResult.Error -> {
                    val messageFromErrors = handlerErrorsApi(network.code)
                    handleError(message = "$messageError\n$messageFromErrors\n [Cód:: ${network.code}]")
                }

                is NetworkResult.Exception -> {
                    handleError(message = "$messageError ${network.exception.message.toString()}")
                }

                is NetworkResult.Success -> {
                    network.data.forEach {
                        _list.add(it)
                    }
                }
                else ->{
                    handleError(message = " Unknown error")
                }
            }
        }
    }

    private fun handleError(message: String) {

    }


}