package com.pose.move.feature.auth.licenseverification

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.theme.MoveTheme
import com.pose.move.ui.widget.MaterialButton
import com.pose.move.ui.widget.Toolbar

@Composable
fun LicenseVerificationScreen(
    onBackButtonClick: () -> Unit,
    onAddLicenseClick: () -> Unit
) {
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
            MaterialButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                text = stringResource(R.string.license_verification_button_text),
                onClick = onAddLicenseClick
            )
        }
    }
}

@Preview
@Composable
private fun LicenseVerificationPreview() {
    MoveTheme {
        LicenseVerificationScreen({}, {})
    }
}