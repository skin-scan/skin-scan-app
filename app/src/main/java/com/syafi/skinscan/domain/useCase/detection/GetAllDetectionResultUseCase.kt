package com.syafi.skinscan.domain.useCase.detection

import com.syafi.skinscan.data.remote.response.detection.DetectionResponse
import com.syafi.skinscan.data.repository.DetectionRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDetectionResultUseCase @Inject constructor(
    private val repo: DetectionRepository
) {

    operator fun invoke(
        token: String,
        query: String?,
        limit: Int?,
        order: String?,
        status: String?,
        page: Int?
    ): Flow<Resource<DetectionResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val resp= repo.getAllDetectionResults(
                    token = token,
                    query = query,
                    limit = limit,
                    order = order,
                    status = status,
                    page = page
                )

                emit(Resource.Success(resp))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: e.localizedMessage.toString()))
            }
        }
}