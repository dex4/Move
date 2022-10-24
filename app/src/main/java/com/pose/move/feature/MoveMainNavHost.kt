package com.pose.move.feature

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.navigation.NavDestination

@Composable
fun MoveMainNavHost(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavDestination.Onboarding.route) {
            OnboardingScreen { navController.navigate("auth") }
        }
    }
}