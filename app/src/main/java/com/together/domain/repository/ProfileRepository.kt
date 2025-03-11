package com.together.domain.repository

import com.together.domain.models.Profile

interface ProfileRepository {
    suspend fun getProfile() : Profile
}