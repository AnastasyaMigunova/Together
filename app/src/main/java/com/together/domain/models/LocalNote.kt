package com.together.domain.models

data class LocalNote (
    val id: String,
    val title: String,
    val content: List<Content>,
    val date: String,
    val isFavourite: Boolean
)