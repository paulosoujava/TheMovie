package com.paulo.mymovie.data.repositories.network.impls

import com.paulo.mymovie.data.repositories.network.apis.Api
import com.paulo.mymovie.data.repositories.network.util.NetworkResult
import com.paulo.mymovie.domain.model.Movie
import com.paulo.mymovie.domain.util.handlerErrorsApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieNetworkImplTest{
    private lateinit var mockServer:  MockWebServer
    private  lateinit var api: Api
    private val mockResponse = MockResponse()

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        api = Retrofit
            .Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)

        mockResponse.setBody(Helper.readFileResource("/movies.json"))

    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun test_get_empty_response() = runBlocking {
        mockResponse.setBody("{}")
        mockServer.enqueue(mockResponse)

        val response = api.getAllMovies()
        assertTrue(response.results == null)
    }

    @Test
    fun test_get_a_profile_by_code_response() = runBlocking {
        mockServer.enqueue(mockResponse)
        val response = api.getAllPopularMovies()
        assertTrue(response.results?.isNotEmpty() == true )
    }

    @Test
    fun check_if_400_response_results_in_an_error_state() = runBlocking {
        assertTrue(handlerErrorsApi(400) == "Bad Request")


    }

}