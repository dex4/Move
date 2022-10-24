@file:OptIn(ExperimentalPagerApi::class)

package com.pose.move.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.pose.move.R
import com.pose.move.feature.onboarding.page.OnboardingPage
import com.pose.move.feature.onboarding.page.OnboardingPageDetails
import com.pose.move.ui.theme.MoveTheme
import com.pose.move.ui.widget.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onCompleteOnboarding: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        HorizontalPager(
            modifier = Modifier.weight(1f),
            count = onboardingPagesDetails.size,
            state = pagerState
        ) { pageIndex ->
            OnboardingPage(
                pageDetails = onboardingPagesDetails[pageIndex],
                onSkipClickListener = if (pageIndex != onboardingPagesDetails.lastIndex) onCompleteOnboarding else null
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPagerIndicator(pagerState = pagerState)
            MaterialButton(
                text = getNextButtonText(pagerState.currentPage),
                endIcon = R.drawable.ic_arrow_forward,
                onClick = { onNextClick(pagerState, coroutineScope, onCompleteOnboarding) })
        }
    }
}

private fun onNextClick(pagerState: PagerState, coroutineScope: CoroutineScope, onCompleteOnboarding: () -> Unit) {
    if (pagerState.currentPage < onboardingPagesDetails.lastIndex) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    } else {
        onCompleteOnboarding.invoke()
    }
}

@Composable
private fun getNextButtonText(currentPage: Int): String =
    if (currentPage < onboardingPagesDetails.lastIndex) {
        stringResource(id = R.string.onboarding_next_button_text)
    } else {
        stringResource(id = R.string.onboarding_start_button_text)
    }

@Preview
@Composable
fun OnboardingScreenPreview() {
    MoveTheme {
        OnboardingScreen {}
    }
}

private val onboardingPagesDetails = listOf(
    OnboardingPageDetails(
        R.drawable.bg_first_onboarding_page,
        R.string.onboarding_first_page_title,
        R.string.onboarding_first_page_description
    ),
    OnboardingPageDetails(
        R.drawable.bg_second_onboarding_page,
        R.string.onboarding_second_page_title,
        R.string.onboarding_second_page_description
    ),
    OnboardingPageDetails(
        R.drawable.bg_third_onboarding_page,
        R.string.onboarding_third_page_title,
        R.string.onboarding_third_page_description
    ),
    OnboardingPageDetails(
        R.drawable.bg_fourth_onboarding_page,
        R.string.onboarding_fourth_page_title,
        R.string.onboarding_fourth_page_description
    )
)