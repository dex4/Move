package com.pose.move.navigation

sealed class NavDestination(val route: String) {

    object Onboarding : NavDestination("onboarding")

    object Authentication : NavDestination("auth")

    object LicenseVerification : NavDestination("license-verification")

    object Home : NavDestination("home")

    object Profile : NavDestination("profile")
}