package com.pose.move.navigation.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pose.move.feature.profile.menu.MenuScreen
import com.pose.move.feature.profile.menu.MenuViewModel
import com.pose.move.navigation.NavDestination

fun NavGraphBuilder.addProfile(navController: NavController) {
    navigation(ProfileDestination.MenuScreen.route, NavDestination.Profile.route) {
        composable(ProfileDestination.MenuScreen.route) {
            val menuViewModel: MenuViewModel = hiltViewModel()

            MenuScreen { clearPhoto: Boolean, clearApp: Boolean ->
                menuViewModel.onClearStorageClick(clearPhoto, clearApp)
                val route = if (clearApp) {
                    NavDestination.Onboarding.route
                } else {
                    NavDestination.Authentication.route
                }
                navController.navigate(
                    route,
                    NavOptions.Builder().setPopUpTo(NavDestination.Home.route, true).build()
                )
            }
        }
    }
}