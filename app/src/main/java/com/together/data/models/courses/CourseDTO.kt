package com.together.data.models.courses

import com.together.data.models.TextDTO

data class CourseDTO (
    val id: String,
    val title: String?,
    val description: String?,
    val tags: List<String>?,
    val text: List<TextDTO>
)