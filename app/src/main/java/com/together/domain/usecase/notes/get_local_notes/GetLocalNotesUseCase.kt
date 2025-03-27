package com.together.domain.usecase.notes.get_local_notes

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getLocalNotes(): List<LocalNoteVO> {
        return noteRepository.getLocalNotes()
            .fold(
                onSuccess = { notes ->
                    notes.map { domainToUiMapper.run { it.toViewObject() } }
                },
                onFailure = { error ->
                    Log.e(
                        "GetLocalNotesUseCase",
                        "Ошибка при загрузке локальных заметок: ${error.message}"
                    )
                    throw error
                }
            )
    }
}