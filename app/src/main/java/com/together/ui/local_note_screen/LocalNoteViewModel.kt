package com.together.ui.local_note_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

//data class LocalNoteState(
//
//)
//
//sealed class LocalNoteEffect {
//    data class ShowError(val message: String) : LocalNoteEffect()
//}
//
//@HiltViewModel
//class LocalNoteViewModel @Inject constructor(
//) : ContainerHost<LocalNoteState, LocalNoteEffect>, ViewModel() {
//
//    override val container = container<LocalNoteState, LocalNoteEffect>(LocalNoteState())
//}