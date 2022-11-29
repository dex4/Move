package com.pose.move.navigation.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pose.move.feature.home.availablescooters.AvailableScootersScreen
import com.pose.move.feature.home.availablescooters.AvailableScootersViewModel
import com.pose.move.navigation.NavDestination

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.addHome(navController: NavController) {
    navigation(HomeDestination.AvailableScootersScreen.route, NavDestination.Home.route) {
        composable(HomeDestination.AvailableScootersScreen.route) {
            val viewModel = hiltViewModel<AvailableScootersViewModel>()
            val itemsList by viewModel.scooters.collectAsStateWithLifecycle()

            AvailableScootersScreen(
                itemsList,
                onSwipeScooterItem = viewModel::changeRevealState,
                onReportIssue = viewModel::reportIssue
            ) {
                navController.navigate(NavDestination.Profile.route)
            }
        }
    }
}