package com.pose.move.navigation

sealed class NavDestination(val route: String) {

    object Onboarding : NavDestination("onboarding")

    object Authentication : NavDestination("auth")

    object Home : NavDestination("home")
}

sealed class AuthenticationDestination(val route: String) {

    object RegisterScreen : AuthenticationDestination("auth/register")

    object LoginScreen : AuthenticationDestination("auth/login")

    object ForgotPasswordScreen : AuthenticationDestination("auth/forgot-password?email={email}") {

        fun createRoute(email: String? = null) = "auth/forgotpassword?email=$email"
    }

    object ResetPassword : AuthenticationDestination("auth/resetpassword?token={token}")
}

sealed class HomeDestination(val route: String) {

    object AvailableScootersScreen : HomeDestination("home/available-scooters")
}