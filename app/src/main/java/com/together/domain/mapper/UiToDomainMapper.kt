package com.together.domain.mapper

import com.together.domain.models.Content
import com.together.domain.models.Course
import com.together.domain.models.LocalNote
import com.together.domain.models.Text
import com.together.ui.models.ContentVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.TextVO
import javax.inject.Inject

class UiToDomainMapper @Inject constructor() {
    fun CourseVO.toDomain(): Course {
        return Course(
            id = "",
            title = title,
            description = description,
            tags = tags,
            textSections = textSections.map { it.toDomain() }
        )
    }

    fun LocalNoteVO.toDomain(): LocalNote {
        return LocalNote(
            id = id,
            title = title,
            content = content.map { it.toDomain() },
            date = date,
            isFavourite = isFavourite
        )
    }

    private fun ContentVO.toDomain(): Content {
        return Content(
            image = image,
            text = text
        )
    }

    private fun TextVO.toDomain(): Text {
        return Text(
            text = this.text,
            image = this.image
        )
    }
}