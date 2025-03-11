package com.together.data.mapper

import com.together.data.models.TextDTO
import com.together.data.models.courses.CourseRequestDTO
import com.together.data.storage.room.entity.ContentEntity
import com.together.data.storage.room.entity.LocalNoteEntity
import com.together.data.storage.room.entity.NoteWithContent
import com.together.domain.models.Content
import com.together.domain.models.Course
import com.together.domain.models.LocalNote
import com.together.domain.models.Text
import javax.inject.Inject

class DomainToDataMapper @Inject constructor() {
    fun Course.toData(): CourseRequestDTO {
        return CourseRequestDTO(
            title = title,
            description = description,
            tags = tags,
            text = textSections.map { it.toData() }
        )
    }

    fun LocalNote.toData(): NoteWithContent {
        val localNoteEntity = LocalNoteEntity(
            id = id,
            title = title,
            date = date,
            isFavourite = isFavourite
        )
        val contentEntities = content.map { it.toData(id) }

        return NoteWithContent(
            localNote = localNoteEntity,
            content = contentEntities
        )
    }

    private fun Content.toData(noteId: String): ContentEntity {
        return ContentEntity(
            noteId = noteId,
            text = text,
            image = image
        )
    }

    private fun Text.toData(): TextDTO {
        return TextDTO(
            text = text,
            image = image
        )
    }
}