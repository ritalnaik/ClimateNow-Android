package com.rital.climatenow.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun WeatherDetailsScreen(
    viewModel: WeatherDetailsViewModel,
    modifier: Modifier = Modifier,
    state: WeatherDetailsUiState,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WeatherHeaderCard(state)
        }

        item {
            WeatherStatsGrid(state)
        }

        item {
            SunInfoCard(
                sunrise = state.sunrise,
                sunset = state.sunset
            )
        }
    }
}

@Composable
fun WeatherHeaderCard(
    state: WeatherDetailsUiState
) {
    Card(
        shape = RoundedCornerShape(28.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = state.city,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Icon(
                imageVector = state.weatherIcon,
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = state.temperature,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.condition,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "H:${state.maxTemp}   L:${state.minTemp}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun WeatherStatsGrid(
    state: WeatherDetailsUiState
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(420.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            WeatherStatCard(
                title = "Humidity",
                value = state.humidity,
                icon = Icons.Rounded.WaterDrop
            )
        }

        item {
            WeatherStatCard(
                title = "Wind",
                value = state.windSpeed,
                icon = Icons.Rounded.Air
            )
        }

        item {
            WeatherStatCard(
                title = "Pressure",
                value = state.pressure,
                icon = Icons.Rounded.Speed
            )
        }

        item {
            WeatherStatCard(
                title = "Visibility",
                value = state.visibility,
                icon = Icons.Rounded.Visibility
            )
        }

        item {
            WeatherStatCard(
                title = "UV Index",
                value = state.uvIndex,
                icon = Icons.Rounded.WbSunny
            )
        }

        item {
            WeatherStatCard(
                title = "Feels Like",
                value = state.feelsLike,
                icon = Icons.Rounded.DeviceThermostat
            )
        }
    }
}

@Composable
fun WeatherStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun SunInfoCard(
    sunrise: String,
    sunset: String
) {

    Card(
        shape = RoundedCornerShape(24.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Rounded.LightMode,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Sunrise")
                Text(sunrise)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Rounded.DarkMode,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Sunset")

                Text(sunset)
            }
        }
    }
}