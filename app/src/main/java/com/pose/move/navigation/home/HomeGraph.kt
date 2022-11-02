package com.pose.move.navigation.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pose.move.feature.home.availablescooters.AvailableScootersScreen
import com.pose.move.feature.home.availablescooters.AvailableScootersViewModel
import com.pose.move.navigation.NavDestination

fun NavGraphBuilder.addHome(navController: NavController) {
    navigation(HomeDestination.AvailableScootersScreen.route, NavDestination.Home.route) {
        composable(HomeDestination.AvailableScootersScreen.route) {
            val availableScootersViewModel: AvailableScootersViewModel = hiltViewModel()

            AvailableScootersScreen { clearPhoto: Boolean, clearApp: Boolean ->
                availableScootersViewModel.onClearStorageClick(clearPhoto, clearApp)
                val route = if (clearApp) {
                    NavDestination.Onboarding.route
                } else {
                    NavDestination.Authentication.route
                }
                navController.navigate(
                    route,
                    NavOptions.Builder().setPopUpTo(HomeDestination.AvailableScootersScreen.route, true).build()
                )
            }
        }
    }
}