package com.pose.move.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {

    fun register(email: String, userName: String, password: String) {
        viewModelScope.launch {
            internalStorageManager.setIsUserLoggedIn(true)
        }
    }
}