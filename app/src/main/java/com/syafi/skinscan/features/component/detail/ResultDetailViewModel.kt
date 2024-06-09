package com.syafi.skinscan.features.component.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse
import com.syafi.skinscan.data.remote.response.detection.detail.DetailedDetection
import com.syafi.skinscan.domain.useCase.detection.DeleteDetectionUseCase
import com.syafi.skinscan.domain.useCase.detection.GetDetectionDetailUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultDetailViewModel @Inject constructor(
    private val getDetectionDetailUseCase: GetDetectionDetailUseCase,
    private val deleteDetectionUseCase: DeleteDetectionUseCase,
    private val pref: UserSessionData
): ViewModel() {

    private val _isDeleteDialogOpen= mutableStateOf(false)
    val isDeleteDialogOpen: State<Boolean> = _isDeleteDialogOpen

    private val _isSuccessDialogOpen= mutableStateOf(false)
    val isSuccessDialogOpen: State<Boolean> = _isSuccessDialogOpen

    private val _token= mutableStateOf<String?>(null)
    val token: State<String?> = _token

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _detectionData= mutableStateOf<DetailedDetection?>(null)
    val detectionData: State<DetailedDetection?> = _detectionData

    fun setDeleteDialogState(state: Boolean) {
        _isDeleteDialogOpen.value= state
    }

    fun setSuccessDialogState(state: Boolean) {
        _isSuccessDialogOpen.value= state
    }

    fun getUserToken() {
        viewModelScope.launch {
            pref.userToken.collect { token ->
                setToken(token)
            }
        }
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun setDetectionData(data: DetailedDetection) {
        _detectionData.value= data
    }

    private fun setToken(token: String) {
        _token.value= token
    }

    fun getDetectionDetail(token: String, id: String): Flow<Resource<DetailDetectionResponse>> {
        var resp: Flow<Resource<DetailDetectionResponse>>? = null

        viewModelScope.launch {
            resp= getDetectionDetailUseCase(token, id)
        }

        return resp as Flow<Resource<DetailDetectionResponse>>
    }

    suspend fun removeDetection(token: String, id: String)=
        deleteDetectionUseCase(token, id)
}