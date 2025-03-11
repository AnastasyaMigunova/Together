package com.together.data.models.notes

import com.together.data.models.AuthorDTO
import com.together.data.models.CommentsDTO
import com.together.data.models.ContentDTO

data class CommunityNoteDTO(
    val id: String,
    val title: String?,
    val content: List<ContentDTO>,
    val author: AuthorDTO,
    val comments: List<CommentsDTO>,
    val date: String
)
