package com.pose.move.feature.licenseverification

sealed class LicenseVerificationDestination(val route: String) {

    object LicenseInformationalScreen : LicenseVerificationDestination("license-verification/license-informational")

    object UploadLicenseScreen : LicenseVerificationDestination("license-verification/upload?licenseImageUri={licenseImageUri}") {

        fun createRoute(licenseImageUri: String) = "license-verification/upload?licenseImageUri=$licenseImageUri"
    }
}