package com.pose.move.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.network.user.RegisterRequest
import com.pose.move.network.user.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager,
    private val userService: UserService
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState>
        get() = _uiState

    private fun register() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = userService.registerUser(
                RegisterRequest(
                    _uiState.value.email,
                    _uiState.value.userName,
                    _uiState.value.password
                )
            )

//            internalStorageManager.setIsUserLoggedIn(result.authToken.isNotEmpty())
            _uiState.value = _uiState.value.copy(isLoading = false, isUserLoggedIn = false)
        }
    }

    fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> _uiState.value = _uiState.value.copy(email = event.email)
            is RegisterEvent.UserNameChanged -> _uiState.value = _uiState.value.copy(userName = event.userName)
            is RegisterEvent.PasswordChanged -> _uiState.value = _uiState.value.copy(password = event.password)
            is RegisterEvent.Register -> register()
        }
    }
}