package com.pose.move.feature.onboarding.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pose.move.R
import com.pose.move.ui.theme.MoveTheme

@Composable
fun OnboardingPage(
    pageDetails: OnboardingPageDetails,
    onSkipClickListener: (() -> Unit)? = null
) {
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(pageDetails.image),
            contentScale = ContentScale.Crop,
            contentDescription = "Onboarding Page Image"
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(pageDetails.title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            if (onSkipClickListener != null) {
                TextButton(
                    onClick = onSkipClickListener
                ) {
                    Text(
                        text = stringResource(R.string.onboarding_skip_button_text),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(R.color.spanish_grey)
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = pageDetails.description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun OnboardingPagePreview() {
    MoveTheme {
        OnboardingPage(
            pageDetails = OnboardingPageDetails(
                R.drawable.bg_first_onboarding_page,
                R.string.onboarding_first_page_title,
                R.string.onboarding_first_page_description
            )
        ) {}
    }
}