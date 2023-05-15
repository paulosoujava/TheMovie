package com.paulo.mymovie.domain.usecases.video

import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.contracts.repositories.IRemoteRepositoryMovie
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.model.Video
import retrofit2.HttpException

class VideoGetAllUseCase(
    private val repository: IRemoteRepositoryMovie
) {

    suspend operator fun invoke(idVideo: String): NetworkResult<List<Video>> {
        return try {
            val result = repository.getAllVideos(idVideo)
            NetworkResult.Success(result)
        } catch (e: HttpException) {
            NetworkResult.Error(e.code(), e.message())
        } catch (t: Throwable) {
            NetworkResult.Exception(t)
        }
    }
}