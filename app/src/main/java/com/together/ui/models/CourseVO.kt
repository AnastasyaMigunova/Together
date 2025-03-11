package com.together.ui.models

data class CourseVO(
    val title: String,
    val description: String,
    val tags: List<String>,
    val textSections: List<TextVO>
)
