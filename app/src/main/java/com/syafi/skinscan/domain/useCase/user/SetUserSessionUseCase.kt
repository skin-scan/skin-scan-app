package com.syafi.skinscan.domain.useCase.user

import com.syafi.skinscan.data.repository.UserRepository
import javax.inject.Inject

class SetUserSessionUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        repo.setUserSession(isCompleted)
    }
}