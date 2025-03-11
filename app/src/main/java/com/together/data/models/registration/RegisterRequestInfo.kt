package com.together.data.models.registration

data class RegisterRequestInfo (
    val phone: String,
    val passwordHashed: String,
    val name: String,
    val surname: String,
    val avatar: String
)