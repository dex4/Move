package com.pose.move.navigation.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.navigation.NavDestination
import com.pose.move.navigation.auth.AuthenticationDestination

fun NavGraphBuilder.addOnboarding(navController: NavController) {
    composable(NavDestination.Onboarding.route) {
        OnboardingScreen {
            navController.navigate(
                AuthenticationDestination.RegisterScreen.route,
                NavOptions.Builder().setPopUpTo(NavDestination.Onboarding.route, true).build()
            )
        }
    }
}