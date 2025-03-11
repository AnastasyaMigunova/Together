package com.together.data.models

data class TokenUserResponse (
    val status: Int,
    val message: String?,
    val data: TokenResponse?
)

data class TokenResponse(
    val token: String
)