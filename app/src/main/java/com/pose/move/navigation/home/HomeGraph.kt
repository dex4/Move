package com.pose.move.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pose.move.feature.home.availablescooters.AvailableScootersScreen
import com.pose.move.navigation.NavDestination

fun NavGraphBuilder.addHome(navController: NavController) {
    navigation(HomeDestination.AvailableScootersScreen.route, NavDestination.Home.route) {
        composable(HomeDestination.AvailableScootersScreen.route) {
            AvailableScootersScreen()
        }
    }
}