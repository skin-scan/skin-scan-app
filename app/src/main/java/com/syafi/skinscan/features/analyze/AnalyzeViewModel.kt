package com.syafi.skinscan.features.analyze

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyzeViewModel @Inject constructor(

): ViewModel() {

    private val _isDialogOpen= mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    private val _photoUri= mutableStateOf<Uri?>(null)
    val photoUri: State<Uri?> = _photoUri

    fun setDialogState(state: Boolean) {
        _isDialogOpen.value= state
    }

    fun setPhotoUri(uri: Uri) {
        _photoUri.value= uri
    }
}