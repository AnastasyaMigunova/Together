package com.together.ui.models

data class CommunityNoteVO (
    val id: String,
    val title: String,
    val content: List<ContentVO>,
    val author: AuthorVO,
    val comment: List<CommentsVO>,
    val date: String
)