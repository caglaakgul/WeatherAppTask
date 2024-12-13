package com.caglaakgul.weatherapptask.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.caglaakgul.weatherapptask.ui.screens.HomeScreen
import com.caglaakgul.weatherapptask.ui.theme.WeatherAppTaskTheme
import com.caglaakgul.weatherapptask.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTaskTheme {
                HomeScreen(viewModel = weatherViewModel)
            }
        }
    }
}