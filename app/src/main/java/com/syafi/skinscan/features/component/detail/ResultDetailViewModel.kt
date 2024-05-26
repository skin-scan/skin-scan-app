package com.syafi.skinscan.features.component.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ResultDetailViewModel @Inject constructor(

): ViewModel() {

    private val _isDeleteDialogOpen= mutableStateOf(false)
    val isDeleteDialogOpen: State<Boolean> = _isDeleteDialogOpen

    private val _isSuccessDialogOpen= mutableStateOf(false)
    val isSuccessDialogOpen: State<Boolean> = _isSuccessDialogOpen

    fun setDeleteDialogState(state: Boolean) {
        _isDeleteDialogOpen.value= state
    }

    fun setSuccessDialogState(state: Boolean) {
        _isSuccessDialogOpen.value= state
    }
}