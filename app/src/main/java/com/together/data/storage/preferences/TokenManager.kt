package com.together.data.storage.preferences

import javax.inject.Inject

class TokenManager @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    fun saveToken(token: String) {
        preferencesManager.saveToken(token)
    }

    fun getToken(): String? {
        return preferencesManager.getToken()
    }

    fun clearToken() {
        preferencesManager.clearToken()
    }
}