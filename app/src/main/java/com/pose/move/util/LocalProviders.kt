package com.pose.move.util

import androidx.compose.runtime.staticCompositionLocalOf
import com.pose.move.MoveActivity
import com.pose.move.ui.widget.announcement.handler.AnnouncementHandler

val LocalActivity = staticCompositionLocalOf<MoveActivity> { error("No MoveActivity") }
val LocalAnnouncementHandler = staticCompositionLocalOf<AnnouncementHandler> { error("No handler") }