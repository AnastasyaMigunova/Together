package com.together.data.storage.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class NoteWithContent(
    @Embedded val localNote: LocalNoteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "noteId"
    )
    val content: List<ContentEntity>
)
