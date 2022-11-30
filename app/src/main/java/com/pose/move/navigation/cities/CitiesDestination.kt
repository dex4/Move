package com.pose.move.navigation.cities

sealed class CitiesDestination(val route: String) {

    object SearchScreen : CitiesDestination("cities/search")
}