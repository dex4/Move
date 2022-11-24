package com.pose.move.util

import androidx.compose.runtime.staticCompositionLocalOf
import com.pose.move.ui.widget.announcement.handler.AnnouncementHandler

val LocalAnnouncementHandler = staticCompositionLocalOf<AnnouncementHandler> { error("No handler") }