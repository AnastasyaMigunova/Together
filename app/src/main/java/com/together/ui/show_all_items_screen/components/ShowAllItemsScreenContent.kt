package com.together.ui.show_all_items_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.together.ui.components.CustomCommunityNoteCard
import com.together.ui.components.CustomCourseCard
import com.together.ui.components.CustomLocalNoteCard
import com.together.ui.error_screen.ErrorScreen
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.utils.ListType
import com.together.ui.show_all_items_screen.ShowItemsState
import com.together.ui.theme.LocalCustomColors

@Composable
fun ShowAllItemsScreenContent(
    state: ShowItemsState
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

        state.listCourses.isNotEmpty() -> {
            SuccessLoadingList(
                listCourses = state.listCourses,
                type = ListType.COURSE_TYPE
            )
        }

        state.listLocalNotes.isNotEmpty() -> {
            SuccessLoadingList(
                listLocalNotes = state.listLocalNotes,
                type = ListType.LOCAL_NOTE_TYPE
            )
        }

        state.listCommunityNotes.isNotEmpty() -> {
            SuccessLoadingList(
                listCommunityNotes = state.listCommunityNotes,
                type = ListType.COMMUNITY_NOTE_TYPE
            )
        }

        state.errorMessage != null -> {
            ErrorScreen()
        }
    }
}

@Composable
fun SuccessLoadingList(
    listCourses: List<CourseVO> = emptyList(),
    listLocalNotes: List<LocalNoteVO> = emptyList(),
    listCommunityNotes: List<CommunityNoteVO> = emptyList(),
    type: String?
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (type) {
            ListType.COURSE_TYPE -> {
                items(listCourses) { course ->
                    CustomCourseCard(
                        title = course.title,
                        tags = course.tags
                    )
                }
            }

            ListType.LOCAL_NOTE_TYPE -> {
                items(listLocalNotes) { note ->
                    CustomLocalNoteCard(localNoteVO = note)
                }
            }

            ListType.COMMUNITY_NOTE_TYPE -> {
                items(listCommunityNotes) { note ->
                    CustomCommunityNoteCard(communityNoteVO = note)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewShowAllItemsScreenContent() {
    ShowAllItemsScreenContent(
        state = ShowItemsState(
            isLoading = false,
            listCourses = emptyList(),
            listLocalNotes = emptyList(),
            listCommunityNotes = emptyList(),
            errorMessage = null
        )
    )
}
