package com.pose.move.feature.profile.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {

    fun onClearStorageClick(clearPhoto: Boolean, clearAppStorage: Boolean) {
        viewModelScope.launch {
            if (clearAppStorage) {
                internalStorageManager.clear()
                return@launch
            }
            if (clearPhoto) {
                internalStorageManager.setHasUserAddedLicensePhoto(false)
            }
            internalStorageManager.setIsUserLoggedIn(false)
        }
    }
}