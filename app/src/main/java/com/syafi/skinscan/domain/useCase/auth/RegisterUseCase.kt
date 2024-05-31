package com.syafi.skinscan.domain.useCase.auth

import com.syafi.skinscan.data.remote.request.RegisterRequest
import com.syafi.skinscan.data.remote.response.auth.AuthResponse
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: UserRepository
) {

    suspend operator fun invoke(request: RegisterRequest): Flow<Resource<AuthResponse>> =
        repo.register(request)
}