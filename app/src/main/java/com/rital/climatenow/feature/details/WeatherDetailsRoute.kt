package com.rital.climatenow.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherDetailsRoute(
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val state by viewModel.uiState.collectAsState()
    WeatherDetailsScreen(
        modifier = modifier,
        viewModel = viewModel,
        state = state
    )
}