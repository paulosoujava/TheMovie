package com.paulo.mymovie.presenter.screens.main

import com.paulo.mymovie.core.BaseEvent
import com.paulo.mymovie.core.BaseUI
import com.paulo.mymovie.domain.model.Movie

data class MainUI(
    override val success: List<Movie> = emptyList(),
    override val errorMessage: String = "",
    override val event: BaseEvent = BaseEvent.LOADING
) : BaseUI<Movie> {

}