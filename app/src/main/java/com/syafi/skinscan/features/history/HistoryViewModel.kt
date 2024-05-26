package com.syafi.skinscan.features.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(

): ViewModel() {
    private val _currTabIndex = mutableIntStateOf(0)
    val currTabIndex: State<Int> = _currTabIndex

    private val _type = mutableStateOf("All")
    val type: State<String> = _type

    fun setIndex(index: Int) {
        _currTabIndex.intValue = index
    }

    fun setType(type: String) {
        _type.value = type
    }
}