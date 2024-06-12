package com.syafi.skinscan.features.upload

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.response.detection.prediction.PredictionResponse
import com.syafi.skinscan.domain.useCase.detection.GetPredictionUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase,
    private val pref: UserSessionData
): ViewModel() {

    private val _isShowDialog = mutableStateOf(false)
    val isShowDialog: State<Boolean> = _isShowDialog

    private val _token= mutableStateOf<String?>(null)
    val token: State<String?> = _token

    private val _photoUri= mutableStateOf<String?>(null)
    val photoUri: State<String?> = _photoUri

    private val _title= mutableStateOf("")
    val title: State<String> = _title

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _predictionResponse= mutableStateOf<Flow<Resource<PredictionResponse>>?>(null)
    val predictionResponse: State<Flow<Resource<PredictionResponse>>?> = _predictionResponse

    private val _predictionResult= mutableStateOf<PredictionResponse?>(null)
    val predictionResult: State<PredictionResponse?> = _predictionResult

    fun setDialogState(state: Boolean) {
        _isShowDialog.value = state
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun setPhotoUri(uri: String) {
        _photoUri.value= uri
    }

    fun setTitle(title: String) {
        _title.value= title
    }

    fun getUserToken() {

        viewModelScope.launch {
            pref.userToken.collect { token ->
                setToken(token)
            }
        }
    }

    fun setPredictionResult(response: PredictionResponse) {
        _predictionResult.value= response
    }

    private fun setToken(token: String) {
        _token.value= token
    }

    private fun setPredictionResponse(predictionResult: Flow<Resource<PredictionResponse>>) {
        _predictionResponse.value= predictionResult
    }

    fun getPrediction(
        token: String,
        multipartBody: MultipartBody.Part,
        reqTitleBody: RequestBody,
    ) {
        setPredictionResponse(getPredictionUseCase(token, multipartBody, reqTitleBody))
    }
}