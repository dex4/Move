package com.pose.move.feature.onboarding.page

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingPageDetails(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
)