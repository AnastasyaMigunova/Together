package com.together.domain.models

data class Profile (
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int,
    val phone: String,
    val registerDate: String,
    val courses: List<Course>,
    val notes: List<CommunityNote>
)