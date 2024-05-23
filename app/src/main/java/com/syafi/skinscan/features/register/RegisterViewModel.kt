package com.syafi.skinscan.features.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.syafi.skinscan.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {

    private val _isShowDialog= mutableStateOf(false)
    val isShowDialog: State<Boolean> = _isShowDialog

    fun setDialogState(state: Boolean) {
        _isShowDialog.value= state
    }
}