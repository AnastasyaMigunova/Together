package com.together.data.mapper

import com.together.data.models.AuthorDTO
import com.together.data.models.CommentsDTO
import com.together.data.models.ContentDTO
import com.together.data.models.TextDTO
import com.together.data.models.courses.CourseDTO
import com.together.data.models.courses.CourseResponseDTO
import com.together.data.models.courses.GetCoursesDTO
import com.together.data.models.notes.CommunityNoteDTO
import com.together.data.models.notes.GetNotesDTO
import com.together.data.models.profile.GetProfileDTO
import com.together.data.storage.room.entity.ContentEntity
import com.together.data.storage.room.entity.NoteWithContent
import com.together.domain.models.Author
import com.together.domain.models.Comments
import com.together.domain.models.CommunityNote
import com.together.domain.models.Content
import com.together.domain.models.Course
import com.together.domain.models.LocalNote
import com.together.domain.models.Profile
import com.together.domain.models.Text
import javax.inject.Inject

class DataToDomainMapper @Inject constructor() {

    fun CourseResponseDTO.toDomain(): Course {
        return Course(
            id = data.id,
            title = data.title,
            description = data.description,
            tags = data.tags,
            textSections = data.text.map { it.toDomain() }
        )
    }

    fun GetCoursesDTO.toDomain(): List<Course> {
        return data.map {
            it.toDomain()
        }
    }

    fun GetNotesDTO.toDomain(): List<CommunityNote> {
        return data.map {
            it.toDomain()
        }
    }

    private fun CommentsDTO.toDomain(): Comments {
        return Comments(
            id = id,
            author = author.toDomain(),
            text = text
        )
    }

    private fun AuthorDTO.toDomain(): Author {
        return Author(
            id = id,
            name = name,
            surname = surname,
            avatar = avatar,
            role = role
        )
    }

    fun GetProfileDTO.toDomain(): Profile {
        return Profile(
            id = data.id,
            name = data.name,
            surname = data.surname,
            avatar = data.avatar,
            role = data.role,
            phone = data.phone,
            registerDate = data.registerDate,
            courses = data.courses.map { it.toDomain() },
            notes = data.notes.map { it.toDomain() }
        )
    }

    fun NoteWithContent.toDomain(): LocalNote {
        return LocalNote(
            id = localNote.id,
            title = localNote.title,
            content = content.map { it.toDomain() },
            date = localNote.date,
            isFavourite = localNote.isFavourite
        )
    }

    fun List<NoteWithContent>.toDomain(): List<LocalNote> {
        return map { it.toDomain() }
    }

    private fun ContentEntity.toDomain(): Content {
        return Content(
            text = text,
            image = image
        )
    }

    private fun CommunityNoteDTO.toDomain(): CommunityNote {
        return CommunityNote(
            id = id,
            title = title,
            content = content.map { it.toDomain() },
            author = author.toDomain(),
            comments = comments.map { it.toDomain() },
            date = date
        )
    }

    private fun CourseDTO.toDomain(): Course {
        return Course(
            id = id,
            title = title,
            description = description,
            tags = tags,
            textSections = text.map { it.toDomain() }
        )
    }

    private fun ContentDTO.toDomain(): Content {
        return Content(
            image = image,
            text = text
        )
    }

    private fun TextDTO.toDomain(): Text {
        return Text(
            text = text,
            image = image
        )
    }
}