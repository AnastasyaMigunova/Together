package com.together.domain.usecase.profile

import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.ProfileRepository
import com.together.ui.models.ProfileVO
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getProfile(): ProfileVO {
        return try {
            val profile = profileRepository.getProfile()
            domainToUiMapper.run { profile.toViewObject() }
        } catch (e: Exception) {
            throw e
        }
    }
}