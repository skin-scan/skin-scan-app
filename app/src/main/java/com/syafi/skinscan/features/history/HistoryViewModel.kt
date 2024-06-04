package com.syafi.skinscan.features.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.response.detection.Detection
import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.domain.useCase.detection.GetAllDetectionResultUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val pref: UserSessionData,
    private val getAllDetectionResultUseCase: GetAllDetectionResultUseCase
): ViewModel() {
    private val _currTabIndex = mutableIntStateOf(0)
    val currTabIndex: State<Int> = _currTabIndex

    private val _searchQuery= mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _token= mutableStateOf<String?>(null)
    val token: State<String?> = _token

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _detectionList= mutableStateOf<List<Detection>>(emptyList())
    val detectionList: State<List<Detection>> = _detectionList

    fun setIndex(index: Int) {
        _currTabIndex.intValue = index
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun getUserToken() {

        viewModelScope.launch {
            pref.userToken.collect { token ->
                setToken(token)
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value= query
    }

    private fun setToken(token: String) {
        _token.value= token
    }

    fun getDetectionResult(
        token: String,
        query: String?,
        limit: Int?,
        order: String?,
        status: String?,
        page: Int?
    ): Flow<Resource<DetectionResponse>> {
        var resp: Flow<Resource<DetectionResponse>>? = null

        viewModelScope.launch {
            resp= getAllDetectionResultUseCase(
                token = token,
                query = query,
                limit = limit,
                order = order,
                status = status,
                page = page
            )
        }

        return resp as Flow<Resource<DetectionResponse>>
    }

    fun setDetectionList(detectionList: List<Detection>) {
        _detectionList.value= detectionList
    }
}