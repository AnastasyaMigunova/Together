package com.together.data.models.profile

import com.together.data.models.courses.CourseDTO
import com.together.data.models.notes.CommunityNoteDTO

data class ProfileDTO(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int,
    val phone: String,
    val registerDate: String,
    val courses: List<CourseDTO>,
    val notes: List<CommunityNoteDTO>
)
