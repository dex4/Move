package com.pose.move.feature.licenseverification

import android.content.ContentResolver
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import android.provider.MediaStore.Images.Media
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.widget.PrimaryButton

@Composable
fun UploadLicenseScreen(
    licenseImageUri: Uri,
    onFindScootersClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg_app), contentScale = ContentScale.Crop)
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(256.dp)
                .aspectRatio(4f / 3f),
            bitmap = getLicenseImageBitmap(licenseImageUri, LocalContext.current.contentResolver),
            contentDescription = "License Image"
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.license_verification_process_done_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = Center
        )
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            text = stringResource(R.string.license_verification_process_done_continue_button_text),
            onClick = onFindScootersClick
        )
    }
}

private fun getLicenseImageBitmap(licenseImageUri: Uri, contentResolver: ContentResolver): ImageBitmap {
    val licenseImage = if (SDK_INT >= P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, licenseImageUri))
    } else {
        @Suppress("DEPRECATION")
        Media.getBitmap(contentResolver, licenseImageUri)
    }

    return licenseImage.asImageBitmap()
}