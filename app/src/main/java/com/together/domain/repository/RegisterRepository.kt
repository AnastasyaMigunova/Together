package com.together.domain.repository

import com.together.domain.models.RegisterCredentials

interface RegisterRepository {
    suspend fun register(registerCredentials: RegisterCredentials): Result<Boolean>
}