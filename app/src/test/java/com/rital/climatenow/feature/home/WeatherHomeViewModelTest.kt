package com.rital.climatenow.feature.home

import com.rital.climatenow.util.MainDispatcherRule
import com.rital.core.utils.StringUtils
import com.rital.domain.model.Weather
import com.rital.domain.usecase.GetCitiesWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class WeatherHomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var useCase: GetCitiesWeatherUseCase
    private lateinit var viewModel: WeatherHomeViewModel
    @Before
    fun setup() {
        useCase = mockk()
    }

    @Test
    fun `getWeather should update uiState with weather list`() =
        runTest {
            val weatherList = listOf(
                Weather(cityName = "Mumbai", temperature = 32.0, weatherCode = 95)
            )
            coEvery {
                useCase(any())
            } returns weatherList
            viewModel = WeatherHomeViewModel(useCase)
            advanceUntilIdle()
            val state = viewModel.uiState.value
            assertEquals(false, state.isLoading)
            assertEquals(weatherList, state.weatherList)
            assertEquals(StringUtils.EMPTY, state.error)
        }

    @Test
    fun `getWeather should update uiState with error`() =
        runTest {
            coEvery {
                useCase(any())
            } throws RuntimeException("Network Error")
            viewModel = WeatherHomeViewModel( useCase  )
            advanceUntilIdle()
            val state = viewModel.uiState.value
            assertEquals(false, state.isLoading)
            assertEquals("Network Error", state.error)
        }
}