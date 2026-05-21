package com.rital.climatenow.feature.home

import com.rital.domain.model.Weather

data class WeatherHomeUiState(
    val isLoading: Boolean = false,
    val weatherList: List<Weather> = emptyList(),
    val error: String = ""
)