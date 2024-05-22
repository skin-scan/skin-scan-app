package com.syafi.skinscan.features.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.util.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref: UserSessionData
) : ViewModel() {

    private val _destination= mutableStateOf(Route.WELCOME_SCREEN)
    val destination: State<String> = _destination

    init {
        viewModelScope.launch {
            pref.isWelcomeScreenCompleted.collect { isCompleted ->
                if (isCompleted) {
                    _destination.value= Route.LOGIN_SCREEN
                }
            }
        }
    }
}