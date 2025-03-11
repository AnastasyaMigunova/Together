package com.together.ui.profile_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCase
import com.together.domain.usecase.profile.GetProfileUseCase
import com.together.ui.models.CourseVO
import com.together.ui.models.LocalNoteVO
import com.together.ui.models.ProfileVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: ProfileVO? = null,
    val lastCourses: List<CourseVO> = emptyList(),
    val lastLocalNote: LocalNoteVO? = null,
    val errorMessage: String? = null
)

sealed class ProfileSideEffect {
    data class NavigateToError(val message: String) : ProfileSideEffect()
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getLastCoursesUseCase: GetLastCoursesUseCase,
    private val getLocalLastNoteUseCase: GetLocalLastNoteUseCase
) : ContainerHost<ProfileState, ProfileSideEffect>, ViewModel() {

    override val container = container<ProfileState, ProfileSideEffect>(ProfileState())

    fun loadProfile() = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val profile = getProfileUseCase.getProfile()
            val courses = getLastCoursesUseCase.getLastCourses()
            val lastLocalNote = getLocalLastNoteUseCase.getLastLocalNote()

            reduce {
                state.copy(
                    isLoading = false,
                    profile = profile,
                    lastCourses = courses,
                    lastLocalNote = lastLocalNote
                )
            }
        } catch (exception: Exception) {
            val errorMessage = exception.message ?: "Неизвестная ошибка"
            reduce { state.copy(isLoading = false, errorMessage = exception.message) }
            postSideEffect(ProfileSideEffect.NavigateToError(errorMessage))
        }
    }
}