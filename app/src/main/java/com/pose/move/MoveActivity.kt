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
import com.pose.move.navigation.NavDestination
import com.pose.move.ui.theme.MoveTheme

class MoveActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalActivity provides this
            ) {
                MoveApp()
            }
        }
    }
}
@Composable
private fun MoveApp() {
    MoveTheme {
        MoveMainNavHost(NavDestination.Authentication.route)
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