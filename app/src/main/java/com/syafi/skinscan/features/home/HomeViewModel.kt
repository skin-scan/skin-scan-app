package com.syafi.skinscan.features.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.syafi.skinscan.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {

    private val _safeDiagnosed= mutableStateOf(150f)
    val safeDiagnosed: State<Float> = _safeDiagnosed

    private val _problemDiagnosed= mutableStateOf(30f)
    val problemDiagnosed: State<Float> = _problemDiagnosed
}