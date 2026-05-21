package com.rital.climatenow.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rital.core.common.cities
import com.rital.core.ui.components.WeatherInfoCard
import com.rital.core.ui.theme.Dimens
import com.rital.domain.model.City

@Composable
fun WeatherHomeScreen(
    modifier: Modifier,
    viewModel: WeatherHomeViewModel = hiltViewModel(),
    state: WeatherHomeUiState,
    onNavigateToDetails: (City) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(Dimens.Medium),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SmallMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.SmallMedium),
                modifier = modifier
            ) {
                items(count = state.weatherList.size) { index ->
                    val city = cities.find { it.name ==  state.weatherList.get(index).cityName }
                    WeatherInfoCard(
                        data = state.weatherList.get(index),
                        onClick = { city?.let{ onNavigateToDetails(it)} }
                    )
                }
            }
        }
    }
}

