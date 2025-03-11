package com.together.domain.models

data class Comments(
    val id: String,
    val author: Author,
    val text: String?
)
