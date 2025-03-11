package com.together.data.repository.auth

import com.together.data.api.ApiService
import com.together.data.models.auth.AuthRequestInfo
import com.together.data.storage.preferences.TokenManager
import com.together.domain.models.AuthCredentials
import com.together.domain.repository.AuthRepository
import com.together.utils.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun auth(authCredentials: AuthCredentials): Result<Boolean> {
        return try {
            val hashedPassword = Utils.hashPassword(authCredentials.password)
            val response =
                apiService.authUser(AuthRequestInfo(authCredentials.phoneNumber, hashedPassword))

            response.data?.let {
                tokenManager.saveToken(it.token)
                Result.success(true)
            } ?: Result.failure(Exception("Неверные данные"))
        } catch (e: HttpException) {
            Result.failure(Exception("Ошибка сервера: ${e.message}"))
        } catch (e: IOException) {
            Result.failure(Exception("Нет подключения к сети"))
        }
    }
}