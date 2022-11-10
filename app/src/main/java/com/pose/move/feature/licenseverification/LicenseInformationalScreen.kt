package com.pose.move.feature.licenseverification

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.core.content.FileProvider
import com.pose.move.BuildConfig.APPLICATION_ID
import com.pose.move.LocalActivity
import com.pose.move.R
import com.pose.move.ui.theme.MoveTheme
import com.pose.move.ui.widget.PrimaryButton
import com.pose.move.ui.widget.Toolbar
import com.pose.move.util.Constants.LicenseVerification.TEMPORARY_LICENSE_PICTURE_FILE_EXTENSION
import com.pose.move.util.Constants.LicenseVerification.TEMPORARY_LICENSE_PICTURE_FILE_NAME
import java.io.File

@Composable
fun LicenseInformationalScreen(
    onBackButtonClick: () -> Unit,
    onGetLicenseImageSuccess: (uri: Uri) -> Unit
) {
    var licenseImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val activity = LocalActivity.current
    val getGalleryImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccessFul ->
        val uri = licenseImageUri
        if (isSuccessFul && uri != null) {
            onGetLicenseImageSuccess(uri)
        } else {
            //TODO: Implement showing error alert
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Toolbar(
            stringResource(id = R.string.license_verification_toolbar_title),
            navIcon = R.drawable.ic_back,
            onNavIconClick = onBackButtonClick
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(R.drawable.bg_license_verification),
                contentScale = Crop,
                contentDescription = "License verification demonstration"
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp),
                text = stringResource(R.string.license_verification_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = stringResource(R.string.license_verification_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                text = stringResource(R.string.license_verification_button_text),
                onClick = {
                    licenseImageUri = createLicensePictureUri(activity)
                    getGalleryImageLauncher.launch(licenseImageUri)
                }
            )
        }
    }
}

private fun createLicensePictureUri(activity: ComponentActivity): Uri {
    val imageFile = File.createTempFile(
        TEMPORARY_LICENSE_PICTURE_FILE_NAME,
        TEMPORARY_LICENSE_PICTURE_FILE_EXTENSION
    ).apply {
        createNewFile()
        deleteOnExit()
    }

    return FileProvider.getUriForFile(
        activity.applicationContext,
        "${APPLICATION_ID}.provider",
        imageFile
    )
}

@Preview
@Composable
private fun LicenseVerificationPreview() {
    MoveTheme {
        LicenseInformationalScreen({}, {})
    }
}