package com.pose.move.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            internalStorageManager.setAuthToken("")
            internalStorageManager.setHasUserAddedLicensePhoto(true)
        }
    }
}