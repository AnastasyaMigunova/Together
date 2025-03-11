package com.together.domain.repository

import com.together.domain.models.AuthCredentials

interface AuthRepository {
    suspend fun auth(authCredentials: AuthCredentials): Result<Boolean>
}