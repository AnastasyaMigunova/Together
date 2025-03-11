package com.together.data.repository.register

import com.together.data.api.ApiService
import com.together.data.models.registration.RegisterRequestInfo
import com.together.data.storage.preferences.TokenManager
import com.together.domain.models.RegisterCredentials
import com.together.domain.repository.RegisterRepository
import com.together.utils.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FakeRegisterRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager
) : RegisterRepository {
    override suspend fun register(registerCredentials: RegisterCredentials): Result<Boolean> {
        return try {
            val fakeToken = "fake_token_123"
            tokenManager.saveToken(fakeToken)
            Result.success(true)
        } catch (e: HttpException) {
            Result.failure(Exception("Ошибка сервера: ${e.message()}"))
        } catch (e: IOException) {
            Result.failure(Exception("Ошибка сервера: ${e.message}"))
        }    }
}