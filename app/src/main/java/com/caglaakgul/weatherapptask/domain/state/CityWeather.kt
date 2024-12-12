package com.caglaakgul.weatherapptask.domain.state

data class CityWeather(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val feelsLike: Double,
    val humidity: Int,
    val uvIndex: Int,
    val iconUrl: String
)