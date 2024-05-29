package com.syafi.skinscan.features.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(

): ViewModel() {

    private val _isDialogOpen= mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    fun setDialogState(state: Boolean) {
        _isDialogOpen.value= state
    }
}