package com.rital.climatenow.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rital.domain.model.City

@Composable
fun WeatherHomeRoute(
    modifier: Modifier,
    onNavigateToDetails: (City) -> Unit
) {
    val viewModel: WeatherHomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    WeatherHomeScreen(
        modifier = modifier,
        viewModel = viewModel,
        state = state,
        onNavigateToDetails = onNavigateToDetails
    )
}