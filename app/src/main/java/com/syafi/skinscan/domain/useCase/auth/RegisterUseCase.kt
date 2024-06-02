package com.syafi.skinscan.domain.useCase.auth

import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke(request: RegisterRequest): Flow<Resource<AuthResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.register(request)
                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.localizedMessage.toString()))
            }
        }
}