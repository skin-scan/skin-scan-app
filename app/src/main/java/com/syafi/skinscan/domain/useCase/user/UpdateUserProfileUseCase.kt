package com.syafi.skinscan.domain.useCase.user

import com.syafi.skinscan.data.remote.response.profile.update.UpdateProfileResponse
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke(
        token: String,
        multipartBody: MultipartBody.Part?,
        reqNameBody: RequestBody,
        reqEmailBody: RequestBody
    ): Flow<Resource<UpdateProfileResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.updateUserProfile(token, multipartBody, reqNameBody, reqEmailBody)
                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.localizedMessage.toString()))
            }
        }
}