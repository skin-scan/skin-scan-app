package com.syafi.skinscan.features.upload

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UploadViewModel @Inject constructor(

): ViewModel() {

    private val _photoUri= mutableStateOf("")
    val photoUri: State<String> = _photoUri

    fun setPhotoUri(uri: String) {
        _photoUri.value= uri
    }
}