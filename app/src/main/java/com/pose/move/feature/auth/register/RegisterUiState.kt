package com.pose.move.feature.auth.register

import com.pose.move.network.ApiOperationResult

data class RegisterUiState(
    val email: String = "",
    val userName: String = "",
    val password: String = "",
    val error: ApiOperationResult.Error? = null,
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
) {
    fun isRegisterFormValid(): Boolean = email.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()
}