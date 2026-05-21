package com.rital.climatenow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rital.climatenow.feature.details.WeatherDetailsRoute
import com.rital.climatenow.feature.home.WeatherHomeRoute
import com.rital.core.utils.NavKeys
import com.rital.core.utils.NumberUtils
import com.rital.core.utils.StringUtils

@Composable
fun AppNavGraph(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost( navController = navController, startDestination = AppRoutes.Home.route) {
        composable(AppRoutes.Home.route) {
            WeatherHomeRoute(
                modifier = modifier,
                onNavigateToDetails = { city ->
                    navController.navigate(
                        AppRoutes.Details.createRoute(
                            city.name,
                            city.latitude,
                            city.longitude
                        )
                    )
                }
            )
        }

        composable(
            AppRoutes.Details.route
        ) { backStackEntry ->
            val city = backStackEntry.arguments ?.getString(NavKeys.CITY_NAME) ?: StringUtils.EMPTY
            val lat =  backStackEntry.arguments ?.getDouble(NavKeys.LATITUDE)   ?: NumberUtils.ZERO_DOUBLE
            val lon =  backStackEntry.arguments ?.getDouble(NavKeys.LONGITUDE) ?: NumberUtils.ZERO_DOUBLE

            WeatherDetailsRoute(
                modifier = modifier
            )
        }
    }
}