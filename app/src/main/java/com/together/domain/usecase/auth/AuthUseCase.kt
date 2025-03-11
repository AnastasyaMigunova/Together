package com.together.domain.usecase.auth

import com.together.domain.models.AuthCredentials
import com.together.domain.repository.AuthRepository
import javax.inject.Inject

private const val MIN_PASSWORD_LENGTH = 6
private val PHONE_NUMBER_REGEX = Regex("^[+]?[78][0-9]{10}$")

sealed class ValidationResult {
    data object Valid : ValidationResult()
    data class Invalid(val errorMessage: String) : ValidationResult()
}

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun auth(phoneNumber: String, password: String): Result<Boolean> {
        val validationResult = validateAuth(phoneNumber, password)

        if (validationResult is ValidationResult.Invalid) {
            return Result.failure(Exception(validationResult.errorMessage))
        }

        return try {
            val credentials = AuthCredentials(phoneNumber = phoneNumber, password = password)
            authRepository.auth(credentials)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun validateAuth(
        phoneNumber: String,
        password: String
    ): ValidationResult {
        if (phoneNumber.isBlank()) return ValidationResult.Invalid("Номер телефона не может быть пустым")
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            return ValidationResult.Invalid("Неправильный формат номера телефона")
        }
        if (password.length < 6) return ValidationResult.Invalid("Пароль должен содержать не менее $MIN_PASSWORD_LENGTH символов")

        return ValidationResult.Valid
    }
}