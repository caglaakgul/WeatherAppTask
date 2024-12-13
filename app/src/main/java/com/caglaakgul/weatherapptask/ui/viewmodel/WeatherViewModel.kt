package com.caglaakgul.weatherapptask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglaakgul.weatherapptask.data.WeatherResponse
import com.caglaakgul.weatherapptask.domain.WeatherRepository
import com.caglaakgul.weatherapptask.domain.state.CityWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _savedCity = MutableStateFlow<String?>(null)
    val savedCity: StateFlow<String?> = _savedCity

    private val _searchResult = MutableStateFlow<CityWeather?>(null)
    val searchResult: StateFlow<CityWeather?> = _searchResult

    fun loadSavedCity() {
        viewModelScope.launch {
            val city = weatherRepository.getSavedCity()
            if (city != null) {
                _savedCity.value = city
                loadWeather(city)
            } else {
                _weatherData.value = null
            }
        }
    }

    fun loadWeather(city: String) {
        viewModelScope.launch {
            val result = weatherRepository.getWeatherForCity(city)
            if (result != null) {
                _weatherData.value = result
                weatherRepository.saveCity(city)
            }
        }
    }

    fun searchCity(city: String) {
        viewModelScope.launch {
            val result = weatherRepository.getWeatherForCity(city)
            if (result != null) {
                _searchResult.value = CityWeather(
                    cityName = result.location.name,
                    temperature = result.current.tempC,
                    condition = result.current.condition.text,
                    feelsLike = result.current.feelsLikeC,
                    humidity = result.current.humidity,
                    uvIndex = result.current.uv.toInt(),
                    iconUrl = result.current.condition.icon
                )
            }
        }
    }
}