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
import com.pose.move.feature.home.availablescooters.AvailableScootersScreen
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.navigation.AuthenticationDestination
import com.pose.move.navigation.HomeDestination
import com.pose.move.navigation.NavDestination

@Composable
fun MoveMainNavHost(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        addOnboarding(navController)
        addAuthenticationGraph(navController)
        addHome(navController)
    }
}

private fun NavGraphBuilder.addAuthenticationGraph(navController: NavController) {
    navigation(AuthenticationDestination.RegisterScreen.route, NavDestination.Authentication.route) {
        composable(AuthenticationDestination.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate(HomeDestination.AvailableScootersScreen.route) },
                onLoginClick = { navController.navigate(AuthenticationDestination.LoginScreen.route) }
            )
        }

        composable(AuthenticationDestination.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(HomeDestination.AvailableScootersScreen.route) },
                onCreateNewAccountClick = { navController.navigateUp() },
                onForgotPasswordClick = { navController.navigate(AuthenticationDestination.ForgotPasswordScreen.createRoute(it)) },
            )
        }

        composable(AuthenticationDestination.ForgotPasswordScreen.route) { backStackEntry ->
            val loginInputEmail = backStackEntry.arguments?.getString("email") ?: ""

            ForgotPasswordScreen(
                loginInputEmail,
                onBackButtonClick = { navController.navigateUp() }
            )
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

private fun NavGraphBuilder.addHome(navController: NavController) {
    navigation(HomeDestination.AvailableScootersScreen.route, NavDestination.Home.route) {
        composable(HomeDestination.AvailableScootersScreen.route) {
            AvailableScootersScreen()
        }
    }
}