package com.pose.move.feature.auth.register

data class RegisterState(
    val email: String = "",
    val userName: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
) {
    fun isRegisterFormValid(): Boolean = email.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()
}