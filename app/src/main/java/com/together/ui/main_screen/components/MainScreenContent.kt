package com.together.ui.main_screen.components

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
import com.together.ui.components.CustomCommunityNoteCard
import com.together.ui.components.CustomLineItems
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.components.CustomViewPager
import com.together.ui.main_screen.MainState
import com.together.ui.models.AuthorVO
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.ContentVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.TextVO
import com.together.ui.theme.LocalCustomColors
import com.together.utils.ListType

@Composable
fun MainScreenContent(
    state: MainState,
    navigateToAllItems: (type: String) -> Unit
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

            state.lastCourses.isNotEmpty() && state.lastCommunityNote != null -> {
                SuccessLoadingData(
                    courses = state.lastCourses,
                    lastCommunityNote = state.lastCommunityNote,
                    lastLocalNote = state.lastLocalNote,
                    navigateToAllItems = navigateToAllItems
                )
            }
        }
    }
}

@Composable
private fun SuccessLoadingData(
    courses: List<CourseVO>,
    lastCommunityNote: CommunityNoteVO,
    lastLocalNote: LocalNoteVO?,
    navigateToAllItems: (type: String) -> Unit
) {
    CustomLineItems(title = stringResource(id = R.string.your_courses)) {
        navigateToAllItems(ListType.COURSE_TYPE)
    }
    CustomViewPager(courses = courses)

    CustomLineItems(title = stringResource(id = R.string.your_notes)) {
        navigateToAllItems(ListType.LOCAL_NOTE_TYPE)
    }
    CustomLocalNoteCard(localNoteVO = lastLocalNote)

    CustomLineItems(title = stringResource(id = R.string.community_notes)) {
        navigateToAllItems(ListType.COMMUNITY_NOTE_TYPE)
    }
    CustomCommunityNoteCard(communityNoteVO = lastCommunityNote)
}

@Preview
@Composable
fun PreviewMainScreenContent() {
    MainScreenContent(
        state = MainState(
            isLoading = false,
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
            lastCommunityNote = CommunityNoteVO(
                id = "1",
                title = "Тест для текста в несколько строк. Это очень важно",
                content = listOf(
                    ContentVO(
                        image = "",
                        text = "Это пример содержимого для CommunityNote. Здесь вы можете найти полезную информацию и советы для начинающих разработчиков."
                    )
                ),
                author = AuthorVO(
                    name = "Имя",
                    surname = "Фамилия",
                    avatar = "",
                    role = ""
                ),
                comment = emptyList(),
                date = "12 июля"
            ),
            lastLocalNote = LocalNoteVO(
                id = "",
                title = "Для создания новой Activity",
                content = listOf(
                    ContentVO(
                        image = "",
                        text = "Это пример содержимого для CommunityNote. Здесь вы можете найти полезную информацию и советы для начинающих разработчиков."
                    )
                ),
                date = "12 июля",
                isFavourite = true
            ),
            errorMessage = null
        ),
        navigateToAllItems = {}
    )
}