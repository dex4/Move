package com.pose.move.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.network.user.RegisterRequest
import com.pose.move.network.user.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager,
    private val userService: UserService
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState>
        get() = _uiState

    fun register(email: String, userName: String, password: String, onRegisterSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = withContext(IO) {
                userService.registerUser(RegisterRequest(userName, email, password))
            }

            internalStorageManager.setIsUserLoggedIn(result.authToken.isNotEmpty())
            onRegisterSuccess()
        }
    }
}

data class RegisterState(
    val email: String? = null,
    val userName: String? = null,
    val password: String? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
) {
    fun isRegisterFormValid(): Boolean =
        !(email.isNullOrEmpty() || userName.isNullOrEmpty() || password.isNullOrEmpty())
}