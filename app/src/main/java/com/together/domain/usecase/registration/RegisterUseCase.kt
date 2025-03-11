package com.together.domain.usecase.registration

import com.together.domain.models.RegisterCredentials
import com.together.domain.repository.RegisterRepository
import javax.inject.Inject

private const val MIN_PASSWORD_LENGTH = 6
private val PHONE_NUMBER_REGEX = Regex("^[+]?[78][0-9]{10}$")

sealed class ValidationResult {
    data object Valid : ValidationResult()
    data class Invalid(val errorMessage: String) : ValidationResult()
}

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend fun register(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ): Result<Boolean> {
        val validationResult = validateRegistration(phoneNumber, password, name, surname)
        if (validationResult is ValidationResult.Invalid) {
            return Result.failure(Exception(validationResult.errorMessage))
        }

        return try {
            val credentials = RegisterCredentials(
                phoneNumber = phoneNumber,
                password = password,
                name = name,
                surname = surname,
                avatar = avatar
            )
            registerRepository.register(credentials)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun validateRegistration(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String
    ): ValidationResult {
        if (phoneNumber.isBlank()) return ValidationResult.Invalid("Номер телефона не может быть пустым")
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            return ValidationResult.Invalid("Неправильный формат номера телефона")
        }
        if (password.length < 6) return ValidationResult.Invalid("Пароль должен содержать не менее $MIN_PASSWORD_LENGTH символов")
        if (name.isBlank()) return ValidationResult.Invalid("Имя не может быть пустым")
        if (surname.isBlank()) return ValidationResult.Invalid("Фамилия не может быть пустой")

        return ValidationResult.Valid
    }
}