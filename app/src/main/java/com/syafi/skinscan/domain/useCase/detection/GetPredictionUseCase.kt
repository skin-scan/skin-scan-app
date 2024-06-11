package com.syafi.skinscan.domain.useCase.detection

import android.util.Log
import com.syafi.skinscan.data.remote.response.detection.prediction.PredictionResponse
import com.syafi.skinscan.data.repository.DetectionRepository
import com.syafi.skinscan.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(
    private val repo: DetectionRepository
) {

    operator fun invoke(
        token: String,
        multipartBody: MultipartBody.Part,
        reqTitleBody: RequestBody,
    ): Flow<Resource<PredictionResponse>> =
        channelFlow {
            send(Resource.Loading())

            try {
                val resp = repo.getPrediction(token, multipartBody, reqTitleBody)
                Log.i("iddd", "invoke: ${resp.data}")
                send(Resource.Success(resp))
            } catch (e: Exception) {
                send(Resource.Error(e.message ?: e.toString()))
            }
        }
}