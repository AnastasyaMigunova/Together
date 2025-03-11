package com.together.data.repository.profile

import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.domain.models.Profile
import com.together.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper
): ProfileRepository {
    override suspend fun getProfile(): Profile {
        return try {
            val response = apiService.getProfile()
            dataToDomainMapper.run { response.toDomain() }
        } catch (e: Exception) {
            throw Exception("Get courses error: ${e.message}", e)
        }
    }
}