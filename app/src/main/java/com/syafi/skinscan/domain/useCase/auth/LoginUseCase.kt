package com.syafi.skinscan.domain.useCase.auth

import com.syafi.skinscan.data.remote.request.LoginRequest
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke(request: LoginRequest): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.login(request)
                emit(Resource.Success(resp.data.token))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.toString()))
            }
        }
}