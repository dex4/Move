package com.pose.move.feature.licenseverification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UploadLicenseViewModel @Inject constructor(
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {

    // TODO: Update this after implementing DB/API
    fun uploadLicense() {
        viewModelScope.launch {
            internalStorageManager.setHasUserAddedLicensePhoto(true)
        }
    }
}