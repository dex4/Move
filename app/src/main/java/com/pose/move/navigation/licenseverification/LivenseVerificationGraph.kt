package com.pose.move.navigation.licenseverification

import android.net.Uri
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pose.move.feature.licenseverification.LicenseVerificationDestination
import com.pose.move.feature.licenseverification.LicenseInformationalScreen
import com.pose.move.feature.licenseverification.UploadLicenseScreen
import com.pose.move.feature.licenseverification.UploadLicenseViewModel
import com.pose.move.navigation.NavDestination

fun NavGraphBuilder.addLicenseVerificationGraph(navController: NavController) {
    navigation(
        LicenseVerificationDestination.LicenseInformationalScreen.route,
        NavDestination.LicenseVerification.route
    ) {
        composable(LicenseVerificationDestination.LicenseInformationalScreen.route) {
            val dispatcher = LocalOnBackPressedDispatcherOwner.current

            LicenseInformationalScreen(
                onBackButtonClick = { dispatcher?.onBackPressedDispatcher?.onBackPressed() },
                onGetLicenseImageSuccess = { uri: Uri ->
                    navController.navigate(
                        LicenseVerificationDestination.UploadLicenseScreen.createRoute(uri.toString()),
                        NavOptions.Builder().setPopUpTo(LicenseVerificationDestination.LicenseInformationalScreen.route, true).build()
                    )
                }
            )
        }

        composable(LicenseVerificationDestination.UploadLicenseScreen.route) { backStackEntry ->
            val licenseImageUri = backStackEntry.arguments?.getString("licenseImageUri")?.toUri()
            requireNotNull(licenseImageUri)
            val uploadLicenseViewModel: UploadLicenseViewModel = hiltViewModel()

            UploadLicenseScreen(licenseImageUri) {
                uploadLicenseViewModel.uploadLicense()
                navController.navigate(
                    NavDestination.Home.route,
                    NavOptions.Builder().setPopUpTo(LicenseVerificationDestination.UploadLicenseScreen.route, true).build()
                )
            }
        }
    }
}