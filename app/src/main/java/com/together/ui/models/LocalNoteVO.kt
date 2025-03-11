package com.together.ui.models

data class LocalNoteVO(
    val id: String,
    val title: String,
    val content: List<ContentVO>,
    val date: String,
    val isFavourite: Boolean
)
