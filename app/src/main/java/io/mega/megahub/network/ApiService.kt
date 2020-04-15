package io.mega.megahub.network

import io.mega.megahub.bean.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/location/{woeid}")
    suspend fun getWeather(@Path("woeid") woeid: Int): WeatherResult
}