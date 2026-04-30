package com.ElOuedUniv.maktaba.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    
    fun onCompleteOnboarding(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userPreferencesRepository.saveOnboardingCompleted(true)
            onSuccess()
        }
    }
}
