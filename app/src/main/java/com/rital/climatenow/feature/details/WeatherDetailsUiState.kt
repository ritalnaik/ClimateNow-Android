package com.rital.climatenow.feature.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector

data class WeatherDetailsUiState(
    val isLoading: Boolean = false,
    val city: String = "",
    val temperature: String = "",
    val maxTemp: String = "",
    val minTemp: String = "",
    val condition: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val pressure: String = "",
    val visibility: String = "",
    val uvIndex: String = "",
    val feelsLike: String = "",
    val sunrise: String = "",
    val sunset: String = "",
    val weatherIcon: ImageVector = Icons.Rounded.WbSunny,
    val error: String? = null
)