package com.paulo.mymovie.core

interface BaseUI<T> {
    val success: List<T>
    val errorMessage: String
    val event: BaseEvent
}