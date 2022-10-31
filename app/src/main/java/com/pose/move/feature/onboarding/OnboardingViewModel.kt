package com.pose.move.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.move.data.preference.InternalStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val internalStorageManager: InternalStorageManager) : ViewModel() {

    fun setOnboardingComplete() {
        viewModelScope.launch {
            internalStorageManager.setIsOnboardingComplete(true)
        }
    }
}