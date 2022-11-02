package com.pose.move.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pose.move.navigation.auth.addAuthenticationGraph
import com.pose.move.navigation.home.addHome
import com.pose.move.navigation.licenseverification.addLicenseVerificationGraph
import com.pose.move.navigation.onboarding.addOnboarding

@Composable
fun MoveMainNavHost(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        addOnboarding(navController)
        addAuthenticationGraph(navController)
        addHome(navController)
        addLicenseVerificationGraph(navController)
    }
}