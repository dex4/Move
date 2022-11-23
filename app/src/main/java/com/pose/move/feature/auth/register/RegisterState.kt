package com.pose.move.feature.auth.register

import com.pose.move.network.ApiOperationResult

data class RegisterState(
    val email: String = "a",
    val userName: String = "a",
    val password: String = "a",
    val error: ApiOperationResult.Error? = null,
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
) {
    fun isRegisterFormValid(): Boolean = email.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()
}