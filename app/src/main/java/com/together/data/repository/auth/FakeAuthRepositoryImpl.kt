package com.together.data.repository.auth

import com.together.data.storage.preferences.TokenManager
import com.together.domain.models.AuthCredentials
import com.together.domain.repository.AuthRepository
import javax.inject.Inject

class FakeAuthRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun auth(authCredentials: AuthCredentials): Result<Boolean> {
        return try {
            if (authCredentials.phoneNumber == "79063199071" && authCredentials.password == "password") {
                val fakeToken = "fake_token_123"
                tokenManager.saveToken(fakeToken)
                Result.success(true)
            } else {
                Result.failure(Exception("Неверные данные"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Ошибка при входе: ${e.message}"))
        }
    }
}
