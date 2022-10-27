package com.pose.move.navigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.pose.move.LocalActivity
import com.pose.move.feature.auth.forgotpassword.ForgotPasswordScreen
import com.pose.move.feature.auth.licenseverification.LicenseVerificationScreen
import com.pose.move.feature.auth.login.LoginScreen
import com.pose.move.feature.auth.register.RegisterScreen
import com.pose.move.feature.auth.resetpassword.ResetPasswordScreen
import com.pose.move.navigation.NavDestination
import com.pose.move.navigation.home.HomeDestination
import com.pose.move.util.Constants

fun NavGraphBuilder.addAuthenticationGraph(navController: NavController) {
    navigation(AuthenticationDestination.RegisterScreen.route, NavDestination.Authentication.route) {
        composable(AuthenticationDestination.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(
                        AuthenticationDestination.LicenseVerificationScreen.route,
                        NavOptions.Builder().setPopUpTo(AuthenticationDestination.RegisterScreen.route, true).build()
                    )
                },
                onLoginClick = { navController.navigate(AuthenticationDestination.LoginScreen.route) }
            )
        }

        composable(AuthenticationDestination.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(
                        HomeDestination.AvailableScootersScreen.route,
                        NavOptions.Builder().setPopUpTo(AuthenticationDestination.LoginScreen.route, true).build()
                    )
                },
                onCreateNewAccountClick = { navController.navigateUp() },
                onForgotPasswordClick = { navController.navigate(AuthenticationDestination.ForgotPasswordScreen.createRoute(it)) },
            )
        }

        composable(AuthenticationDestination.LicenseVerificationScreen.route) {
            val ac = LocalActivity.current

            LicenseVerificationScreen(
                onBackButtonClick = { ac.onBackPressedDispatcher.onBackPressed() },
                onAddLicenseClick = { }
            )
        }

        composable(AuthenticationDestination.ForgotPasswordScreen.route) { backStackEntry ->
            val loginInputEmail = backStackEntry.arguments?.getString("email") ?: ""
            ForgotPasswordScreen(
                loginInputEmail,
                onBackButtonClick = { navController.navigateUp() }
            )
        }

        composable(
            AuthenticationDestination.ResetPassword.route,
            deepLinks = listOf(navDeepLink { uriPattern = Constants.ResetPassword.RESET_PASSWORD_URI_PATTERN })
        ) { backStackEntry ->
            val resetToken = backStackEntry.arguments?.getString("token") ?: ""
//            if (isUserAuthenticated) {
//                navController.navigate(
//                    NavDestination.Home.route,
//                    NavOptions.Builder().setPopUpTo(AuthenticationDestination.ResetPassword.route, true).build()
//                )
//                return@composable
//            }
            ResetPasswordScreen(
                resetToken,
                onBackClick = {
                    navController.navigate(
                        NavDestination.Authentication.route,
                        NavOptions.Builder().setPopUpTo(AuthenticationDestination.ResetPassword.route, true).build()
                    )
                },
                onPasswordResetSuccess = {
                    navController.navigate(
                        AuthenticationDestination.LoginScreen.route,
                        NavOptions.Builder().setPopUpTo(AuthenticationDestination.ResetPassword.route, true).build()
                    )
                }
            )
        }
    }
}