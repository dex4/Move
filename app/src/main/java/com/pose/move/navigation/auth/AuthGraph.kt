package com.pose.move.navigation.auth

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.pose.move.feature.auth.forgotpassword.ForgotPasswordScreen
import com.pose.move.feature.auth.login.LoginScreen
import com.pose.move.feature.auth.login.LoginViewModel
import com.pose.move.feature.auth.register.RegisterEvent
import com.pose.move.feature.auth.register.RegisterScreen
import com.pose.move.feature.auth.register.RegisterState
import com.pose.move.feature.auth.register.RegisterViewModel
import com.pose.move.feature.auth.resetpassword.ResetPasswordScreen
import com.pose.move.navigation.NavDestination
import com.pose.move.navigation.home.HomeDestination
import com.pose.move.util.Constants

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.addAuthenticationGraph(navController: NavController) {
    navigation(AuthenticationDestination.RegisterScreen.route, NavDestination.Authentication.route) {
        composable(AuthenticationDestination.RegisterScreen.route) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            val uiState: RegisterState by registerViewModel.uiState.collectAsStateWithLifecycle()

            RegisterScreen(
                registerState = uiState,
                handleEvent = registerViewModel::handleEvent,
                onLoginClick = { navController.navigate(AuthenticationDestination.LoginScreen.route) }
            )

            LaunchedEffect(uiState) {
                if (uiState.isUserLoggedIn) {
                    navController.navigate(
                        NavDestination.LicenseVerification.route,
                        NavOptions.Builder().setPopUpTo(NavDestination.Authentication.route, true).build()
                    )
                } else if (uiState.error != null) {
                    //TODO: Implement snackbar-like error behavior & handle event on user dismiss
                    Log.d("WRKR", uiState.error.toString())
                    registerViewModel.handleEvent(RegisterEvent.ClearError)
                }
            }
        }

        composable(AuthenticationDestination.LoginScreen.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                onLoginClick = { email: String, password: String ->
                    loginViewModel.login(email, password)
                    navController.navigate(
                        HomeDestination.AvailableScootersScreen.route,
                        NavOptions.Builder().setPopUpTo(NavDestination.Authentication.route, true).build()
                    )
                },
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