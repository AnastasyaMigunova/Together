package com.together.domain.usecase.auth

import com.together.data.storage.preferences.TokenManager
import javax.inject.Inject

class CheckUserLoggedInUseCase @Inject constructor(
    private val tokenManager: TokenManager
) {
    fun checkUserLogged(): Boolean {
        return tokenManager.getToken() != null
    }
}