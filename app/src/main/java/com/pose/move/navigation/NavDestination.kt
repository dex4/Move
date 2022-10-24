package com.pose.move.navigation

sealed class NavDestination(val route: String) {

    object Onboarding : NavDestination("onboarding")
}