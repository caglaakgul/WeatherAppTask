package com.caglaakgul.weatherapptask.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>) {
}