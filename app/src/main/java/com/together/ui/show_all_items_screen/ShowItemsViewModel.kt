package com.together.ui.show_all_items_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.courses.get_courses.GetCoursesUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityNotesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalNotesUseCase
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.utils.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class ShowItemsState(
    val isLoading: Boolean = false,
    val listCourses: List<CourseVO> = emptyList(),
    val listLocalNotes: List<LocalNoteVO> = emptyList(),
    val listCommunityNotes: List<CommunityNoteVO> = emptyList(),
    val errorMessage: String? = null
)

sealed class ShowItemsSideEffect {
    data object ShowError : ShowItemsSideEffect()
}

@HiltViewModel
class ShowItemsViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getCommunityNotesUseCase: GetCommunityNotesUseCase,
    private val getLocalNotesUseCase: GetLocalNotesUseCase
) : ContainerHost<ShowItemsState, ShowItemsSideEffect>, ViewModel() {

    override val container = container<ShowItemsState, ShowItemsSideEffect>(ShowItemsState())

    fun loadList(type: String) = intent {
        reduce { state.copy(isLoading = true) }

        try {
            when (type) {
                ListType.COURSE_TYPE -> loadCourses()
                ListType.LOCAL_NOTE_TYPE -> loadLocalNotes()
                ListType.COMMUNITY_NOTE_TYPE -> loadCommunityNotes()
                else -> handleUnknownTypeError()
            }
        } catch (exception: Exception) {
            handleException(exception)
        }
    }

    private suspend fun loadCourses() = intent {
        val courses = getCoursesUseCase.getCourses()
        reduce {
            state.copy(
                isLoading = false,
                listCourses = courses,
                errorMessage = null
            )
        }
    }

    private suspend fun loadLocalNotes() = intent {
        val localNotes = getLocalNotesUseCase.getLocalNotes()
        reduce {
            state.copy(
                isLoading = false,
                listLocalNotes = localNotes,
                errorMessage = null
            )
        }
    }

    private suspend fun loadCommunityNotes() = intent {
        val communityNotes = getCommunityNotesUseCase.getAllCommunityNotes()
        reduce {
            state.copy(
                isLoading = false,
                listCommunityNotes = communityNotes,
                errorMessage = null
            )
        }
    }

    private fun handleUnknownTypeError() = intent {
        reduce {
            state.copy(
                isLoading = false,
                errorMessage = "Неизвестный тип данных"
            )
        }
        postSideEffect(ShowItemsSideEffect.ShowError)
    }

    private fun handleException(exception: Exception) = intent {
        reduce {
            state.copy(
                isLoading = false,
                errorMessage = exception.message ?: "Неизвестная ошибка"
            )
        }
        postSideEffect(ShowItemsSideEffect.ShowError)
    }
}