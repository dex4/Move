package com.pose.move.feature.splash

import androidx.lifecycle.ViewModel
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.navigation.NavDestination
import com.pose.move.navigation.auth.AuthenticationDestination
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class SplashViewModel @Inject constructor(private val internalStorageManager: InternalStorageManager) : ViewModel() {

    val startDestination: String
        get() =
            runBlocking {
                val isUserLoggedIn = internalStorageManager.isUserLoggedIn.first()
                val hasUserAddedLicensePhoto = internalStorageManager.hasUserAddedLicensePhoto.first()
                when {
                    isUserLoggedIn && hasUserAddedLicensePhoto -> NavDestination.Home.route
                    isUserLoggedIn && !hasUserAddedLicensePhoto -> AuthenticationDestination.LicenseVerificationScreen.route
                    internalStorageManager.isOnboardingComplete.first() -> NavDestination.Authentication.route
                    else -> NavDestination.Onboarding.route
                }
            }
}