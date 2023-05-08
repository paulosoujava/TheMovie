package com.paulo.mymovie.data.repositories.network.util

import com.google.gson.annotations.SerializedName

data class ResultNetwork<T : Any>(
    val id: Int?,
    val page: Int?,
    val results: T?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?
)
