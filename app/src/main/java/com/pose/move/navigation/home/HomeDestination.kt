package com.pose.move.navigation.home

sealed class HomeDestination(val route: String) {

    object AvailableScootersScreen : HomeDestination("home/available-scooters")
}