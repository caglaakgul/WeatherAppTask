package com.caglaakgul.weatherapptask.domain

import com.caglaakgul.weatherapptask.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun getWeatherForCity(
        @Query("key") apiKey: String = "29b44702afa04360a36165251241012",
        @Query("q") city: String
    ): Response<WeatherResponse>
}