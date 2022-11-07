package com.pose.move.navigation.profile

sealed class ProfileDestination(val route: String) {

    object MenuScreen : ProfileDestination("profile/menu")
}
