package com.together.ui.models

data class ProfileVO(
    val name: String,
    val surname: String,
    val avatar: String,
    val role: String,
    val phone: String,
    val registerDate: String,
    val courses: List<CourseVO>,
    val notes: List<CommunityNoteVO>
)
