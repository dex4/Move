package com.pose.move.ui.widget.inputfield

import androidx.annotation.DrawableRes

sealed class TrailIconBehavior {

    data class PasswordToggle(
        @DrawableRes
        val passwordShownIcon: Int,
        @DrawableRes
        val passwordHiddenIcon: Int
    ) : TrailIconBehavior()

    data class Action(
        @DrawableRes
        val actionIcon: Int
    ) : TrailIconBehavior()

    object None : TrailIconBehavior()
}
