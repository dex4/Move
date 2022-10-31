package com.pose.move

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pose.move.navigation.MoveMainNavHost
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.feature.splash.SplashViewModel
import com.pose.move.ui.theme.MoveTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoveActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalActivity provides this
            ) {
                MoveAppUI(viewModel.startDestination)
            }
        }
    }
}

@Composable
private fun MoveAppUI(startDestination: String) {
    MoveTheme {
        MoveMainNavHost(startDestination)
    }
}

val LocalActivity = staticCompositionLocalOf<MoveActivity> {
    error("No MoveActivity provided")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoveTheme {
        OnboardingScreen {}
    }
}