package com.together.domain.models

data class Course(
    val id: String,
    val title: String?,
    val description: String?,
    val tags: List<String>?,
    val textSections: List<Text>
)
