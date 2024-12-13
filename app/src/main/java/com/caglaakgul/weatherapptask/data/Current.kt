package com.caglaakgul.weatherapptask.data

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    val humidity: Int,
    @SerializedName("uv") val uv: Double,
    val condition: Condition
)