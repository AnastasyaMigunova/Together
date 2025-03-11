package com.together.data.models.auth

data class AuthRequestInfo(
    val phone: String,
    val passwordHashed: String
)
