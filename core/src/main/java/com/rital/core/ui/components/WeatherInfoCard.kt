package com.rital.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import com.rital.core.ui.theme.*
import com.rital.domain.model.Weather

@Composable
fun WeatherInfoCard(
    data: Weather,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Press animation
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    val cardColors = getWeatherCardColors(data.weatherCode)

    // Animated gradient background
    val animatedGradient by animateColorAsState(
        targetValue = cardColors.gradientEnd,
        animationSpec = tween(500),
        label = "gradient"
    )
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .scale(scale)
            .shadow(
                elevation = if (isPressed) Dimens.ExtraSmall else Dimens.SmallMedium,
                shape = RoundedCornerShape(Dimens.Large),
                ambientColor = cardColors.shadowColor,
                spotColor = cardColors.shadowColor
            )
            .clip(RoundedCornerShape(Dimens.Large))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(Dimens.Large),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(cardColors.gradientStart, animatedGradient)
                    )
                )
                .padding(Dimens.Medium)
        ) {
            // Weather icon with animation
            AnimatedWeatherIcon(
                code = data.weatherCode,
                tint = cardColors.iconTint,
                modifier = Modifier.align(Alignment.TopEnd).size(Dimens.ExtraLarge)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Location
                Text(
                    text = data.cityName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    ),
                    color = cardColors.textPrimary,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.weight(1f))

                // Temperature
                Text(
                    text = "${data.temperature}°",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 56.sp
                    ),
                    color = cardColors.textPrimary
                )

                // Description
//                Text(
//                    text = data.description,
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        fontWeight = FontWeight.Medium
//                    ),
//                    color = cardColors.textSecondary,
//                    maxLines = 1
//                )
            }
        }
    }

}

@Composable
private fun AnimatedWeatherIcon(
    code : Int,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "iconAnimation")

    // Floating animation for cloud/sun
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )

    // Rotation for sun
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val icon = getWeatherIcon(code)

    Icon(
        imageVector = icon,
        tint = tint,
        contentDescription = getWeatherCondition(code),
        modifier = modifier
            .offset(y = offsetY.dp)
            .then(
                if (code == 0) {
                    Modifier // Could add rotation here if desired
                } else Modifier
            )
    )
}

// --- Helper Functions ---
@Composable
private fun getWeatherIcon(code: Int ): ImageVector {
    return when (code) {
        0 -> Icons.Rounded.WbSunny
        1,2,3 -> Icons.Rounded.Cloud
        61,63,65 -> Icons.Rounded.WaterDrop
        96,99-> Icons.Rounded.Thunderstorm
        77,85,86 -> Icons.Rounded.AcUnit
        45,48-> Icons.Rounded.CloudOff
        95 -> Icons.Rounded.Air
        else-> Icons.Rounded.Air
    }
}

data class WeatherCardColors(
    val gradientStart: Color,
    val gradientEnd: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val iconTint: Color,
    val shadowColor: Color
)
@Composable
private fun getWeatherCardColors(code: Int): WeatherCardColors {
    return when (code) {
        0 -> WeatherCardColors(
            gradientStart = SunnyGradientStart,
            gradientEnd = SunnyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.85f),
            iconTint = Color.White.copy(alpha = 0.95f),
            shadowColor = SunnyGradientEnd.copy(alpha = 0.4f)
        )
        1,2,3  -> WeatherCardColors(
            gradientStart = CloudyGradientStart,
            gradientEnd = CloudyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.8f),
            iconTint = Color.White.copy(alpha = 0.9f),
            shadowColor = CloudyGradientEnd.copy(alpha = 0.3f)
        )
        61, 63, 65  -> WeatherCardColors(
            gradientStart = RainyGradientStart,
            gradientEnd = RainyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.8f),
            iconTint = Color.White.copy(alpha = 0.9f),
            shadowColor = RainyGradientEnd.copy(alpha = 0.4f)
        )
        95 -> WeatherCardColors(
            gradientStart = StormyGradientStart,
            gradientEnd = StormyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.75f),
            iconTint = Color(0xFFFFD54F),
            shadowColor = StormyGradientEnd.copy(alpha = 0.5f)
        )
       77 -> WeatherCardColors(
            gradientStart = SnowyGradientStart,
            gradientEnd = SnowyGradientEnd,
            textPrimary = Color(0xFF37474F),
            textSecondary = Color(0xFF546E7A),
            iconTint = Color(0xFF90CAF9),
            shadowColor = SnowyGradientEnd.copy(alpha = 0.3f)
        )
        51, 53, 55-> WeatherCardColors(
            gradientStart = PartlyCloudyGradientStart,
            gradientEnd = PartlyCloudyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.85f),
            iconTint = Color.White.copy(alpha = 0.95f),
            shadowColor = PartlyCloudyGradientEnd.copy(alpha = 0.3f)
        )
        45,48  -> WeatherCardColors(
            gradientStart = FoggyGradientStart,
            gradientEnd = FoggyGradientEnd,
            textPrimary = Color(0xFF37474F),
            textSecondary = Color(0xFF607D8B),
            iconTint = Color(0xFF78909C),
            shadowColor = FoggyGradientEnd.copy(alpha = 0.2f)
        )
       else -> WeatherCardColors(
            gradientStart = WindyGradientStart,
            gradientEnd = WindyGradientEnd,
            textPrimary = Color.White,
            textSecondary = Color.White.copy(alpha = 0.85f),
            iconTint = Color.White.copy(alpha = 0.9f),
            shadowColor = WindyGradientEnd.copy(alpha = 0.3f)
        )
    }
}

fun getWeatherCondition(
    code: Int
): String {
    return when(code) {
        // Clear sky
        0 -> "Sunny"
        // Mainly clear, partly cloudy, overcast
        1, 2, 3 -> "Cloudy"
        // Fog
        45, 48 -> "Fog"
        // Drizzle
        51, 53, 55 -> "Drizzle"
        // Freezing drizzle
        56, 57 -> "Freezing Drizzle"
        // Rain
        61, 63, 65 -> "Rainy"
        // Freezing rain
        66, 67 -> "Freezing Rain"
        // Snow fall
        71, 73, 75 -> "Snowy"
        // Snow grains
        77 -> "Snowy"
        // Rain showers
        80, 81, 82 -> "Rain Showers"
        // Snow showers
        85, 86 -> "Snow Showers"
        // Thunderstorm
        95 -> "Thunderstorm"
        // Thunderstorm with hail
        96, 99 -> "Thunderstorm"
        else -> "Unknown"
    }
}