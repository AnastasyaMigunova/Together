package com.together.domain.usecase.profile

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.ProfileRepository
import com.together.ui.models.ProfileVO
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getProfile(): ProfileVO {
        return profileRepository.getProfile()
            .fold(
                onSuccess = { profile -> domainToUiMapper.run { profile.toViewObject() } },
                onFailure = { error ->
                    Log.e(
                        "GetProfileUseCase",
                        "Ошибка при загрузке профиля: ${error.message}"
                    )
                    throw error
                }
            )
    }
}