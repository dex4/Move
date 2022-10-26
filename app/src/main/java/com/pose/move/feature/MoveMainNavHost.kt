package com.pose.move.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pose.move.feature.auth.forgotpassword.ForgotPasswordScreen
import com.pose.move.feature.auth.login.LoginScreen
import com.pose.move.feature.auth.register.RegisterScreen
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.navigation.AuthenticationDestination
import com.pose.move.navigation.NavDestination

@Composable
fun MoveMainNavHost(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        addOnboarding(navController)
        addAuthenticationGraph(navController)
    }
}

private fun NavGraphBuilder.addAuthenticationGraph(navController: NavController) {
    navigation(AuthenticationDestination.RegisterScreen.route, NavDestination.Authentication.route) {
        composable(AuthenticationDestination.RegisterScreen.route) {
            RegisterScreen { navController.navigate(AuthenticationDestination.LoginScreen.route) }
        }

        composable(AuthenticationDestination.LoginScreen.route) {
            LoginScreen()
        }

        composable(AuthenticationDestination.ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
    }
}

private fun NavGraphBuilder.addOnboarding(navController: NavController) {
    composable(NavDestination.Onboarding.route) {
        OnboardingScreen {
            navController.navigate(
                AuthenticationDestination.RegisterScreen.route,
                NavOptions.Builder().setPopUpTo(NavDestination.Onboarding.route, true).build()
            )
        }
    }
}