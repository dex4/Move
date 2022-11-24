package com.pose.move.ui.widget.announcement

data class AnnouncementData(
    val text: String,
    val type: AnnouncementType,
    val actionButtonText: String? = null
)