package com.pose.move.ui.widget.announcement.handler

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.pose.move.ui.widget.announcement.AnnouncementData

fun interface AnnouncementHandler {

    suspend fun announce(announcementData: AnnouncementData): AnnouncementResult

    companion object {

        @Composable
        fun rememberAnnouncementHandler(
            snackbarHostState: SnackbarHostState,
            onAnnouncementDataChanged: (AnnouncementData) -> Unit
        ): AnnouncementHandler = remember {
            AnnouncementHandler { announcementData ->
                onAnnouncementDataChanged(announcementData)

                return@AnnouncementHandler when (snackbarHostState.showSnackbar("")) {
                    SnackbarResult.ActionPerformed -> AnnouncementResult.ActionPerformed
                    SnackbarResult.Dismissed -> AnnouncementResult.Dismissed
                }
            }
        }
    }
}