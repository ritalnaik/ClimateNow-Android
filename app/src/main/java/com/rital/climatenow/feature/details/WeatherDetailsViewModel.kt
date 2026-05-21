package com.rital.climatenow.feature.details

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rital.climatenow.feature.home.WeatherHomeUiState
import com.rital.core.common.cities
import com.rital.core.ui.extensions.toWeatherIcon
import com.rital.core.utils.NavKeys
import com.rital.core.utils.NumberUtils
import com.rital.core.utils.StringUtils
import com.rital.domain.enums.WeatherCondition
import com.rital.domain.usecase.GetCitiesWeatherUseCase
import com.rital.domain.usecase.GetWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String


@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherDetailsUiState())
    val uiState = _uiState.asStateFlow()
    init {
        val name = savedStateHandle.get<String>(NavKeys.CITY_NAME)?:StringUtils.EMPTY
        val latitude = savedStateHandle.get<String>(NavKeys.LATITUDE)?.toDouble()?: NumberUtils.ZERO_DOUBLE
        val longitude = savedStateHandle.get<String>(NavKeys.LONGITUDE)?.toDouble()?: NumberUtils.ZERO_DOUBLE
        getWeatherDetails(name,latitude,longitude)
    }

    private fun getWeatherDetails(name:String, latitude:Double, longitude:Double) {
        viewModelScope.launch {
            Log.d("WeatherDetailsViewModel", "Inputs: ${name}, ${latitude}, ${longitude}")
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val result = getWeatherDetailsUseCase(name =name,latitude =latitude,longitude = longitude)
                Log.d("WeatherDetailsViewModel", "zresultttt: ${result}")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    city = result.city,
                    temperature = "${result.temperature}"+ StringUtils.CELSIUS,
                    maxTemp = "${result.maxTemperature}"+ StringUtils.CELSIUS,
                    minTemp = "${result.minTemperature}"+ StringUtils.CELSIUS,
                    condition = result.condition,
                    humidity = "${result.humidity}"+ StringUtils.PERCENT,
                    windSpeed = "${result.windSpeed} "+ StringUtils.KM_PER_HOUR,
                    pressure = "${result.pressure} "+ StringUtils.HPA,
                    visibility = "${result.visibility} "+ StringUtils.METERS,
                    uvIndex = result.uvIndex.toString(),
                    feelsLike = "${result.feelsLike}"+ StringUtils.CELSIUS,
                    sunrise = result.sunrise,
                    sunset = result.sunset,
                    weatherIcon = WeatherCondition.valueOf(result.condition).toWeatherIcon()
                )
            } catch (e: Exception) {
                Log.d("WeatherDetailsViewModel", "Error fetching weather details: ${e.message}")
                _uiState.value = _uiState.value.copy(
                    isLoading = false
                )
            }
        }
    }
}