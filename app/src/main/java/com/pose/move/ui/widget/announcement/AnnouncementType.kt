package com.pose.move.ui.widget.announcement

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.pose.move.R

enum class AnnouncementType(
    @DrawableRes val icon: Int,
    @ColorRes val backgroundColor: Int,
    @ColorRes val contentColor: Int,
    @ColorRes val actionColor: Int
) {
    Success(
        R.drawable.ic_success,
        R.color.announcement_success_background_color,
        R.color.announcement_content_color,
        R.color.announcement_success_action_color
    ),
    Warning(
        R.drawable.ic_warning,
        R.color.announcement_warning_background_color,
        R.color.announcement_content_color,
        R.color.announcement_warning_action_color
    ),
    Error(
        R.drawable.ic_error,
        R.color.announcement_error_background_color,
        R.color.announcement_content_color,
        R.color.announcement_error_action_color
    ),
    Info(
        R.drawable.ic_info,
        R.color.announcement_info_background_color,
        R.color.announcement_info_content_color,
        R.color.announcement_info_action_color
    )
}