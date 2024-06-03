package com.syafi.skinscan.domain.useCase.detection

import com.syafi.skinscan.data.remote.response.detection.detail.DetailDetectionResponse
import com.syafi.skinscan.data.repository.DetectionRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetectionDetailUseCase @Inject constructor(
    private val repo: DetectionRepository
) {

    operator fun invoke(token: String, id: String): Flow<Resource<DetailDetectionResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.getDetectionDetail(token, id)
                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.localizedMessage.toString()))
            }
        }
}