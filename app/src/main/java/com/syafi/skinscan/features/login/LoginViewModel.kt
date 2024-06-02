package com.syafi.skinscan.features.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.domain.useCase.auth.LoginUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val pref: UserSessionData
) :ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _isShowPassword = mutableStateOf(false)
    val isShowPassword: State<Boolean> = _isShowPassword

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    private val _token= mutableStateOf<String?>(null)
    val token: State<String?> = _token

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun setPasswordVisibility(isShown: Boolean) {
        _isShowPassword.value = isShown
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login(request: LoginRequest): Flow<Resource<String>> {
        var resp: Flow<Resource<String>>? = null
        viewModelScope.launch {
            resp= useCase(request)
        }

        return resp as Flow<Resource<String>>
    }

    fun getUserToken() {

        viewModelScope.launch {
            pref.userToken.collect { token ->
                setToken(token)
            }
        }
    }

    private fun setToken(token: String) {
        _token.value= token
    }
}