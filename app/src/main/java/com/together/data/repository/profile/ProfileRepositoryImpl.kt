package com.together.data.repository.profile

import android.util.Log
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.domain.models.Profile
import com.together.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper
): ProfileRepository {
    override suspend fun getProfile(): Result<Profile> {
        return runCatching {
            val response = apiService.getProfile()
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("ProfileRepository", "Get profile error: ${it.message}")
        }
    }
}