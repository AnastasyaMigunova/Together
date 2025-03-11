package com.together.ui.main_screen

import androidx.lifecycle.ViewModel
import com.together.data.repository.courses.FakeCourseRepositoryImpl
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityLastNoteUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCase
import com.together.ui.models.CommunityNoteVO
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class MainState(
    val isLoading: Boolean = false,
    val lastCourses: List<CourseVO> = emptyList(),
    val lastCommunityNote: CommunityNoteVO? = null,
    val lastLocalNote: LocalNoteVO? = null,
    val errorMessage: String? = null
)

sealed class MainSideEffect {
    data class NavigateToError(val message: String) : MainSideEffect()
    data class NavigateTo(val type: String) : MainSideEffect()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastCoursesUseCase: GetLastCoursesUseCase,
    private val getCommunityLastNoteUseCase: GetCommunityLastNoteUseCase,
    private val getLocalLastNoteUseCase: GetLocalLastNoteUseCase
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun loadData() = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val courses = getLastCoursesUseCase.getLastCourses()
            val lastCommunityNote = getCommunityLastNoteUseCase.getLastCommunityNote()
            val lastLocalNote = getLocalLastNoteUseCase.getLastLocalNote()

            reduce {
                state.copy(
                    isLoading = false,
                    lastCourses = courses,
                    lastCommunityNote = lastCommunityNote,
                    lastLocalNote = lastLocalNote,
                    errorMessage = null
                )
            }

        } catch (exception: Exception) {
            val errorMessage = exception.message ?: "Неизвестная ошибка"
            reduce { state.copy(isLoading = false, errorMessage = exception.message) }
            postSideEffect(MainSideEffect.NavigateToError(errorMessage))
        }
    }

    fun navigate(type:String) = intent {
        postSideEffect(MainSideEffect.NavigateTo(type))
    }
}
