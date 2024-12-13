package com.caglaakgul.weatherapptask.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caglaakgul.weatherapptask.domain.state.CityWeather
import com.caglaakgul.weatherapptask.ui.components.CityWeatherCard
import com.caglaakgul.weatherapptask.ui.components.SearchBar
import com.caglaakgul.weatherapptask.ui.components.WeatherDetail
import com.caglaakgul.weatherapptask.ui.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()
    val savedCity by viewModel.savedCity.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()
    var selectedCity by remember { mutableStateOf<CityWeather?>(null) }
    var showInitialMessage by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadSavedCity()
        savedCity?.let {
            viewModel.loadWeather(it)
            showInitialMessage = false
        }
    }

    Column {
        SearchBar(onSearch = { city ->
            selectedCity = null
            viewModel.searchCity(city)
            showInitialMessage = false
        })

        when {
            selectedCity != null -> {
                WeatherDetail(
                    cityWeather = selectedCity!!
                )
            }

            searchResult != null -> {
                CityWeatherCard(
                    cityWeather = searchResult!!,
                    onCitySelected = { cityName ->
                        selectedCity = searchResult
                        viewModel.loadWeather(cityName)
                    }
                )
            }

            weatherData != null -> {
                WeatherDetail(
                    cityWeather = CityWeather(
                        cityName = weatherData!!.location.name,
                        temperature = weatherData!!.current.tempC,
                        condition = weatherData!!.current.condition.text,
                        feelsLike = weatherData!!.current.feelsLikeC,
                        humidity = weatherData!!.current.humidity,
                        uvIndex = weatherData!!.current.uv.toInt(),
                        iconUrl = weatherData!!.current.condition.icon
                    )
                )
            }

            showInitialMessage -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("No City Selected", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Please Search For A City", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}