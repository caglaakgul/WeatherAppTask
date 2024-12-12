package com.caglaakgul.weatherapptask.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.caglaakgul.weatherapptask.data.WeatherResponse
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) {

    suspend fun getWeatherForCity(city: String): WeatherResponse? {
        return try {
            val response = apiService.getWeatherForCity(city = city)
            if (response.isSuccessful) {
                response.body()
            } else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveCity(city: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CITY] = city
        }
    }

    suspend fun getSavedCity(): String? {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CITY]
        }.firstOrNull()
    }
}

object PreferencesKeys {
    val CITY = stringPreferencesKey("saved_city")
}
