package com.pose.move.feature.auth.register

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pose.move.navigation.NavDestination
import com.pose.move.navigation.auth.AuthenticationDestination
import com.pose.move.network.ApiResponse
import com.pose.move.ui.widget.announcement.AnnouncementData
import com.pose.move.ui.widget.announcement.AnnouncementType
import com.pose.move.util.LocalAnnouncementHandler
import com.pose.move.ui.widget.announcement.handler.AnnouncementResult

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RegisterRoute(navController: NavController) {
    val registerViewModel: RegisterViewModel = hiltViewModel()
    val uiState: RegisterUiState by registerViewModel.uiState.collectAsStateWithLifecycle()
    val announcementHandler = LocalAnnouncementHandler.current

    RegisterScreen(
        registerUiState = uiState,
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
            val result = announcementHandler(
                AnnouncementData(
                    (uiState.error as? ApiResponse.Error.ApiException)?.message ?: "",
                    AnnouncementType.Error
                )
            )
            when (result) {
                AnnouncementResult.ActionPerformed -> Log.d("WRKR", "Snackbar action clicked")
                AnnouncementResult.Dismissed -> Log.d("WRKR", "Snackbar was dismissed")
            }
            registerViewModel.handleEvent(RegisterEvent.ClearError)
        }
    }
}