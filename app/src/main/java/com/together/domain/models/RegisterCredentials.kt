package com.together.domain.models

data class RegisterCredentials (
    val phoneNumber: String,
    val password: String,
    val name: String,
    val surname: String,
    val avatar: String
)