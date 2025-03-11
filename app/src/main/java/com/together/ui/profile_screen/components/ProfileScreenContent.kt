package com.together.ui.profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.R
import com.together.ui.components.CustomLineItems
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.CustomProfileCard
import com.together.ui.components.CustomViewPager
import com.together.ui.models.AuthorVO
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.ProfileVO
import com.together.ui.models.TextVO
import com.together.ui.profile_screen.ProfileState
import com.together.ui.theme.LocalCustomColors
import com.together.utils.Utils.fillWidthOfParent

@Composable
fun ProfileScreenContent(
    state: ProfileState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = LocalCustomColors.current.backgroundYellow
                    )
                }
            }

            state.profile != null && state.lastCourses.isNotEmpty() -> {
                SuccessLoadingProfile(state.profile, state.lastCourses, state.lastLocalNote)
            }
        }
    }
}

@Composable
fun SuccessLoadingProfile(
    profile: ProfileVO,
    courses: List<CourseVO>,
    lastLocalNoteVO: LocalNoteVO?
) {
    CustomProfileCard(
        modifier = Modifier.fillWidthOfParent(16.dp),
        profile = profile
    )

    CustomLineItems(title = stringResource(id = R.string.your_courses)) {}
    CustomViewPager(courses = courses)

    CustomLineItems(title = stringResource(id = R.string.your_notes)) {}
    CustomLocalNoteCard(localNoteVO = lastLocalNoteVO)
}

@Preview
@Composable
fun PreviewProfileScreenContent() {
    ProfileScreenContent(
        state = ProfileState(
            isLoading = false,
            profile = ProfileVO(
                name = "Имя",
                surname = "Фамилия",
                avatar = "",
                role = "Админ",
                phone = "+ 7 (906) 319-90-71",
                registerDate = "12 июля",
                courses = listOf(
                    CourseVO(
                        title = "Основы Андроида",
                        description = "",
                        tags = listOf(
                            "View",
                            "Компоненты андроид",
                            "Создание проекта",
                            "Intent",
                            "Manifest"
                        ),
                        textSections = listOf(
                            TextVO(
                                image = "",
                                text = ""
                            )
                        )
                    )
                ),
                notes = listOf(
                    CommunityNoteVO(
                        id = "1",
                        title = "Тест для текста в несколько строк. Это очень важно",
                        content = emptyList(),
                        author = AuthorVO(
                            name = "Имя",
                            surname = "Фамилия",
                            avatar = "",
                            role = ""
                        ),
                        comment = emptyList(),
                        date = "12 июля"
                    )
                ),
            ),
            lastCourses = listOf(
                CourseVO(
                    title = "Основы Андроида",
                    description = "",
                    tags = listOf(
                        "View",
                        "Компоненты андроид",
                        "Создание проекта",
                        "Intent",
                        "Manifest"
                    ),
                    textSections = listOf(
                        TextVO(
                            image = "",
                            text = ""
                        )
                    )
                )
            ),
            lastLocalNote = null,
            errorMessage = null
        )
    )
}