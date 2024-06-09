package com.syafi.skinscan.domain.useCase.auth

import com.syafi.skinscan.data.remote.request.ChangePasswordRequest
import com.syafi.skinscan.data.remote.response.auth.changePassword.ChangePasswordResponse
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke(
        token: String,
        changePasswordRequest: ChangePasswordRequest
    ): Flow<Resource<ChangePasswordResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp = repo.changePassword(token, changePasswordRequest)
                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.toString()))
            }
        }
}