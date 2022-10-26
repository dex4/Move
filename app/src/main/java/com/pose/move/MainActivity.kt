package com.pose.move

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pose.move.feature.MoveMainNavHost
import com.pose.move.feature.onboarding.OnboardingScreen
import com.pose.move.navigation.NavDestination
import com.pose.move.ui.theme.MoveTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MoveTheme {
                MoveMainNavHost(NavDestination.Authentication.route)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoveTheme {
        OnboardingScreen {}
    }
}