package com.together.data.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }

    private companion object {
        private const val TOKEN_KEY = "token"
        private const val USER_PREFS = "user_prefs"
    }
}