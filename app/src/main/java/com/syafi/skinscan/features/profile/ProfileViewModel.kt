package com.syafi.skinscan.features.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {

    private val _isDialogOpen= mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    fun setDialogState(state: Boolean) {
        _isDialogOpen.value= state
    }
}