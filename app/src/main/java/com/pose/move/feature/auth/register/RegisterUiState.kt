package com.pose.move.feature.auth.register

import com.pose.move.network.ApiResponse

data class RegisterUiState(
    val email: String = "",
    val userName: String = "",
    val password: String = "",
    val error: ApiResponse.Error? = null,
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
) {
    fun isRegisterFormValid(): Boolean = email.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()
}