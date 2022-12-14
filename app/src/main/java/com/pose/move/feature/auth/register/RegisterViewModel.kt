package com.pose.move.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.network.ApiOperationResult
import com.pose.move.network.user.UserRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager,
    private val userRemoteDataSource: UserRemoteDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState>
        get() = _uiState

    private fun register() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            val result = userRemoteDataSource.registerUser(
                _uiState.value.userName,
                _uiState.value.email,
                _uiState.value.password
            )

            when (result) {
                is ApiOperationResult.Success -> {
                    internalStorageManager.setAuthToken(result.value.authToken)
                    _uiState.value = _uiState.value.copy(isLoading = false, isUserLoggedIn = true, error = null)
                }
                is ApiOperationResult.Error ->
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, isUserLoggedIn = false, error = result)
            }
        }
    }

    fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> register()
            is RegisterEvent.EmailChanged -> _uiState.value = _uiState.value.copy(email = event.email, error = null)
            is RegisterEvent.UserNameChanged -> _uiState.value = _uiState.value.copy(userName = event.userName, error = null)
            is RegisterEvent.PasswordChanged -> _uiState.value = _uiState.value.copy(password = event.password, error = null)
            is RegisterEvent.ClearError -> _uiState.value = _uiState.value.copy(error = null)
        }
    }
}