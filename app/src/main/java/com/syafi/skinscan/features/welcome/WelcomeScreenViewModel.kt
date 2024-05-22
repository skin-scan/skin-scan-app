package com.syafi.skinscan.features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.domain.useCase.user.SetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val setSessionUseCase: SetUserSessionUseCase
): ViewModel() {

    fun setUserSession(isCompleted: Boolean) {
        viewModelScope.launch {
            setSessionUseCase.invoke(isCompleted)
        }
    }
}