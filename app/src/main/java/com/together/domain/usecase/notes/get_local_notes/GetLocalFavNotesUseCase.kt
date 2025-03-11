package com.together.domain.usecase.notes.get_local_notes

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalFavNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getLocalFavNotes(): List<LocalNoteVO> {
        return noteRepository.getFavLocalNotes()
            .mapCatching { notes ->
                notes.map { note -> domainToUiMapper.run { note.toViewObject() } }
            }
            .getOrElse { error ->
                Log.e(
                    "GetLocalFavNotesUseCase",
                    "Ошибка при загрузке любимых заметок: ${error.message}"
                )
                throw error
            }
    }
}