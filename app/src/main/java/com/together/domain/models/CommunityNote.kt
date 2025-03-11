package com.together.domain.models

data class CommunityNote(
    val id: String,
    val title: String?,
    val content: List<Content>,
    val author: Author,
    val comments: List<Comments>,
    val date: String
)
