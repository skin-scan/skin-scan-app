package com.syafi.skinscan.features.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import com.syafi.skinscan.data.remote.response.profile.UserData
import com.syafi.skinscan.domain.useCase.detection.GetAllDetectionResultUseCase
import com.syafi.skinscan.domain.useCase.user.GetProfileUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase,
    private val pref: UserSessionData
): ViewModel() {

    private val _isDialogOpen= mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    private val _token= mutableStateOf<String?>(null)
    val token: State<String?> = _token

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _userProfileData= mutableStateOf<UserData?>(null)
    val userProfileData: State<UserData?> = _userProfileData

    private val _safeDiagnosed= mutableStateOf(0f)
    val safeDiagnosed: State<Float> = _safeDiagnosed

    private val _problemDiagnosed= mutableStateOf(0f)
    val problemDiagnosed: State<Float> = _problemDiagnosed

    fun setDialogState(state: Boolean) {
        _isDialogOpen.value= state
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

    private fun setToken(token: String) {
        _token.value= token
    }

    fun getUserProfile(token: String): Flow<Resource<ProfileResponse>> {
        var resp: Flow<Resource<ProfileResponse>>? = null
        viewModelScope.launch {
            resp= profileUseCase(token)
        }

        return resp as Flow<Resource<ProfileResponse>>
    }

    fun setUserProfileData(userData: UserData) {
        _userProfileData.value= userData
        _safeDiagnosed.value= userData.summary.safeCount.toFloat()
        _problemDiagnosed.value= userData.summary.diagnosedCount.toFloat()
    }
}