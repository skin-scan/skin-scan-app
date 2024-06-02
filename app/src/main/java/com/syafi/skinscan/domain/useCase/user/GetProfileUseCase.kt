package com.syafi.skinscan.domain.useCase.user

import com.syafi.skinscan.data.remote.response.profile.ProfileResponse
import com.syafi.skinscan.data.repository.UserRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke(token: String): Flow<Resource<ProfileResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.getUserProfile(token)
                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.localizedMessage.toString()))
            }
        }
}