package com.ElOuedUniv.maktaba.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.repository.UserPreferencesRepository
import com.ElOuedUniv.maktaba.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val hasCompletedOnboarding = userPreferencesRepository.hasCompletedOnboarding.first()
            _startDestination.value = if (hasCompletedOnboarding) {
                Screen.BookList.route
            } else {
                Screen.Onboarding.route
            }
        }
    }
}
