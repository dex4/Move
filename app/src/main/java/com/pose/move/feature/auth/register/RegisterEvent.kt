package com.pose.move.feature.auth.register

sealed class RegisterEvent {

    class EmailChanged(val email: String) : RegisterEvent()

    class UserNameChanged(val userName: String) : RegisterEvent()

    class PasswordChanged(val password: String) : RegisterEvent()

    object Register : RegisterEvent()
}