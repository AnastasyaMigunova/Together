package com.together.data.models.courses

import com.together.data.models.TextDTO

data class CourseRequestDTO(
    val title: String?,
    val description: String?,
    val tags: List<String>?,
    val text: List<TextDTO>
)
