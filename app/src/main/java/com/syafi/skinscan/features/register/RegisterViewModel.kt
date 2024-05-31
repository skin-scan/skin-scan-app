package com.syafi.skinscan.features.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.domain.useCase.auth.RegisterUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
): ViewModel() {

    private val _isShowDialog= mutableStateOf(false)
    val isShowDialog: State<Boolean> = _isShowDialog

    private val _name= mutableStateOf("")
    val name: State<String> = _name

    private val _email= mutableStateOf("")
    val email: State<String> = _email

    private val _password= mutableStateOf("")
    val password: State<String> = _password

    private val _isLoading= mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _isShowPassword= mutableStateOf(false)
    val isShowPassword: State<Boolean> = _isShowPassword

    private val _message= mutableStateOf("")
    val message: State<String> = _message

    fun setDialogState(state: Boolean) {
        _isShowDialog.value= state
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value= state
    }

    fun setPasswordVisibility(isShown: Boolean) {
        _isShowPassword.value= isShown
    }

    fun setName(name: String) {
        _name.value= name
    }

    fun setEmail(email: String) {
        _email.value= email
    }

    fun setPassword(password: String) {
        _password.value= password
    }

    fun setMessage(message: String) {
        _message.value= message
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            useCase(request).collect {
                when (it) {
                    is Resource.Error -> {
                        setLoadingState(false)
                        setMessage(it.message.toString())
                    }
                    is Resource.Loading -> setLoadingState(true)
                    is Resource.Success -> {
                        setLoadingState(false)
                        setMessage(it.message.toString())
                    }
                }
            }
        }
    }
}