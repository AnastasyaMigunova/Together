package com.together.data.models

data class CommentsDTO(
    val id: String,
    val author: AuthorDTO,
    val text: String?
)