package com.pose.move

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pose.move.feature.splash.SplashViewModel
import com.pose.move.navigation.MoveMainNavHost
import com.pose.move.ui.theme.MoveTheme
import com.pose.move.ui.widget.announcement.Announcement
import com.pose.move.ui.widget.announcement.AnnouncementData
import com.pose.move.ui.widget.announcement.handler.AnnouncementHandler.Companion.rememberAnnouncementHandler
import com.pose.move.util.LocalAnnouncementHandler
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
            MoveAppUI(viewModel.startDestination)
        }
    }
}

@Composable
private fun MoveAppUI(startDestination: String) {
    val snackbarHostState = remember { SnackbarHostState() }
    var announcementData: AnnouncementData? by remember { mutableStateOf(null) }
    val handler = rememberAnnouncementHandler(snackbarHostState) { announcementData = it }

    MoveTheme {
        CompositionLocalProvider(
            LocalAnnouncementHandler provides handler
        ) {
            MoveMainNavHost(startDestination)
            SnackbarHost(
                modifier = Modifier.animateContentSize(),
                hostState = snackbarHostState,
                snackbar = {
                    val data = requireNotNull(announcementData)
                    Announcement(announcementData = data,
                        onAlertDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
                        onActionPerformed = { snackbarHostState.currentSnackbarData?.performAction() }
                    )
                    //TODO: Add button callback
                }
            )
        }
    }
}