package com.pose.move.navigation.cities

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pose.move.feature.cities.SearchScreen
import com.pose.move.navigation.NavDestination

fun NavGraphBuilder.addCities(navController: NavController) {
    navigation(CitiesDestination.SearchScreen.route, NavDestination.Cities.route) {
        composable(CitiesDestination.SearchScreen.route) {
            SearchScreen()
        }
    }
}