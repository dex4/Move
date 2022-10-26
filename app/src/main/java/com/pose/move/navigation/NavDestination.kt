package com.pose.move.navigation

sealed class NavDestination(val route: String) {

    object Onboarding : NavDestination("onboarding")

    object Authentication : NavDestination("auth")
}

sealed class AuthenticationDestination(val route: String) {

    object RegisterScreen : AuthenticationDestination("auth/register")

    object LoginScreen : AuthenticationDestination("auth/login")

    object ForgotPasswordScreen : AuthenticationDestination("auth/forgot-password?email={email}") {

        fun createRoute(email: String? = null) = "auth/forgot-password?email=$email"
    }
}