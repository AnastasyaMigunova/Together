package com.together.ui.favourites_screen

import androidx.lifecycle.ViewModel
import com.together.domain.usecase.notes.get_local_notes.GetLocalFavNotesUseCase
import com.together.ui.main_screen.MainSideEffect
import com.together.ui.models.LocalNoteVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


data class FavouritesState(
    val isLoading: Boolean = false,
    val localFavNotes: List<LocalNoteVO> = emptyList(),
    val errorMessage: String? = null
)

sealed class FavouritesSideEffect {
    data class ShowError(val message: String) : FavouritesSideEffect()
}

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getLocalFavNotesUseCase: GetLocalFavNotesUseCase
): ContainerHost<FavouritesState, FavouritesSideEffect>, ViewModel() {

    override val container = container<FavouritesState, FavouritesSideEffect>(FavouritesState())

    fun loadLocalLastFavNote() = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val localFavNotes = getLocalFavNotesUseCase.getLocalFavNotes()

            reduce {
                state.copy(
                    isLoading = false,
                    localFavNotes = localFavNotes
                )
            }

        } catch (exception: Exception) {
            val errorMessage = exception.message ?: "Неизвестная ошибка"
            reduce { state.copy(isLoading = false, errorMessage = exception.message) }
            postSideEffect(FavouritesSideEffect.ShowError(errorMessage))
        }
    }
}