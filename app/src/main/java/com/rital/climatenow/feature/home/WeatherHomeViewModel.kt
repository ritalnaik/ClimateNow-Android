package com.rital.climatenow.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rital.core.common.cities
import com.rital.domain.usecase.GetCitiesWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val getCitiesWeatherUseCase: GetCitiesWeatherUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherHomeUiState())
    val uiState = _uiState.asStateFlow()
    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val result = getCitiesWeatherUseCase(cities)
                Log.d("WeatherHomeViewModel", "Fetched weather data: $result")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                        weatherList = result
                )
            } catch (e: Exception) {
                Log.e("WeatherHomeViewModel" ,"Error fetching weather data", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }
}