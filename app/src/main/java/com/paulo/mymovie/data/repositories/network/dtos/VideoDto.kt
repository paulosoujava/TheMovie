package com.paulo.mymovie.data.repositories.network.dtos

import com.paulo.mymovie.domain.model.Video


class VideoDto(
    val name: String,
    val key: String,
) {
    fun toEntity() = Video(
        name = name,
        key = key
    )
}