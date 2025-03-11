package com.together.domain.mapper

import com.together.domain.models.Author
import com.together.domain.models.Comments
import com.together.domain.models.CommunityNote
import com.together.domain.models.Content
import com.together.domain.models.Course
import com.together.domain.models.LocalNote
import com.together.domain.models.Profile
import com.together.domain.models.Text
import com.together.ui.models.AuthorVO
import com.together.ui.models.CommentsVO
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.ContentVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.ProfileVO
import com.together.ui.models.TextVO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class DomainToUiMapper @Inject constructor() {

    fun Course.toViewObject(): CourseVO {
        return CourseVO(
            title = title ?: "",
            description = description ?: "",
            tags = tags ?: listOf(""),
            textSections = textSections.map { it.toViewObject() }
        )
    }

    fun CommunityNote.toViewObject(): CommunityNoteVO {
        return CommunityNoteVO(
            id = id,
            title = title ?: "",
            content = content.map { it.toViewObject() },
            author = author.toViewObject(),
            comment = comments.map { it.toViewObject() },
            date = formatDate(date)
        )
    }

    fun LocalNote.toViewObject(): LocalNoteVO {
        return LocalNoteVO(
            id = id,
            title = title,
            content = content.map { it.toViewObject() },
            date = date,
            isFavourite = isFavourite
        )
    }

    fun Profile.toViewObject(): ProfileVO {
        return ProfileVO(
            name = name,
            surname = surname,
            avatar = avatar,
            role = formatRole(role),
            phone = formatPhone(phone),
            registerDate = formatRegisterDate(registerDate),
            courses = courses.map { it.toViewObject() },
            notes = notes.map { it.toViewObject() }
        )
    }

    fun Comments.toViewObject(): CommentsVO {
        return CommentsVO(
            author = author.toViewObject(),
            text = text ?: ""
        )
    }

    fun Content.toViewObject(): ContentVO {
        return ContentVO(
            image = image,
            text = text ?: ""
        )
    }

    fun Author.toViewObject(): AuthorVO {
        return AuthorVO(
            name = name,
            surname = surname,
            avatar = avatar,
            role = role
        )
    }

    private fun Text.toViewObject(): TextVO {
        return TextVO(
            text = text ?: "",
            image = image
        )
    }

    private fun formatRole(inputRole: Int): String {
        return when (inputRole) {
            0 -> "Студент"
            2 -> "Админ"
            else -> "Неизвестная роль"
        }
    }

    private fun formatPhone(inputPhone: String): String {
        return when {
            inputPhone.startsWith("7") && inputPhone.length == 11 -> {
                "+7 (${inputPhone.substring(1, 4)}) ${
                    inputPhone.substring(
                        4,
                        7
                    )
                }-${inputPhone.substring(7, 9)}-${inputPhone.substring(9, 11)}"
            }

            inputPhone.startsWith("8") && inputPhone.length == 11 -> {
                "8 (${inputPhone.substring(1, 4)}) ${
                    inputPhone.substring(
                        4,
                        7
                    )
                }-${inputPhone.substring(7, 9)}-${inputPhone.substring(9, 11)}"
            }

            else -> {
                inputPhone
            }
        }
    }

    private fun formatRegisterDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime = LocalDateTime.parse(inputDate, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return dateTime.format(outputFormatter)
    }

    private fun formatDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime = LocalDateTime.parse(inputDate, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
        return dateTime.format(outputFormatter)
    }
}
