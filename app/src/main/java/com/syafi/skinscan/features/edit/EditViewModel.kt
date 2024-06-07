package com.syafi.skinscan.features.edit

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafi.skinscan.data.local.dataStore.UserSessionData
import com.syafi.skinscan.data.remote.request.ChangePasswordRequest
import com.syafi.skinscan.data.remote.response.auth.changePassword.ChangePasswordResponse
import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import com.syafi.skinscan.domain.useCase.auth.ChangePasswordUseCase
import com.syafi.skinscan.domain.useCase.user.UpdateUserProfileUseCase
import com.syafi.skinscan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val pref: UserSessionData
): ViewModel() {

    private val _isDialogOpen = mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    private val _photoUri = mutableStateOf<Uri?>(null)
    val photoUri: State<Uri?> = _photoUri

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _oldPassword = mutableStateOf("")
    val oldPassword: State<String> = _oldPassword

    private val _newPassword = mutableStateOf("")
    val newPassword: State<String> = _newPassword

    private val _confirmNewPassword = mutableStateOf("")
    val confirmNewPassword: State<String> = _confirmNewPassword

    private val _isShowOldPassword = mutableStateOf(false)
    val isShowOldPassword: State<Boolean> = _isShowOldPassword

    private val _isShowNewPassword = mutableStateOf(false)
    val isShowNewPassword: State<Boolean> = _isShowNewPassword

    private val _isShowNewConfirmPassword = mutableStateOf(false)
    val isShowNewConfirmPassword: State<Boolean> = _isShowNewConfirmPassword

    private val _token = mutableStateOf<String?>(null)
    val token: State<String?> = _token

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun setDialogState(state: Boolean) {
        _isDialogOpen.value = state
    }

    private fun setToken(token: String) {
        _token.value = token
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

    fun setPhotoUri(uri: Uri) {
        _photoUri.value = uri
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setOldPassword(oldPassword: String) {
        _oldPassword.value = oldPassword
    }

    fun setNewPassword(newPassword: String) {
        _newPassword.value = newPassword
    }

    fun setConfirmNewPassword(confirmNewPassword: String) {
        _confirmNewPassword.value = confirmNewPassword
    }

    fun setOldPasswordVisibility(isShown: Boolean) {
        _isShowOldPassword.value = isShown
    }

    fun setNewPasswordVisibility(isShown: Boolean) {
        _isShowNewPassword.value = isShown
    }

    fun setNewConfirmPasswordVisibility(isShown: Boolean) {
        _isShowNewConfirmPassword.value = isShown
    }

    fun updateProfile(
        token: String,
        multipartBody: MultipartBody.Part?,
        reqNameBody: RequestBody,
        reqEmailBody: RequestBody,

        ): Flow<Resource<UpdateProfileResponse>> {
        var resp: Flow<Resource<UpdateProfileResponse>>? = null

        viewModelScope.launch {
            resp = updateUserProfileUseCase(token, multipartBody, reqNameBody, reqEmailBody)
        }

        return resp as Flow<Resource<UpdateProfileResponse>>
    }

    fun changePassword(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): Flow<Resource<ChangePasswordResponse>> {

        var resp: Flow<Resource<ChangePasswordResponse>>? = null

        viewModelScope.launch {
            resp = changePasswordUseCase(token, changePasswordRequest)
        }

        return resp as Flow<Resource<ChangePasswordResponse>>
    }
}