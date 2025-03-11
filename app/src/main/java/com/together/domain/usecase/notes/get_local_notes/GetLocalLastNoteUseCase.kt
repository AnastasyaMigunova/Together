package com.together.domain.usecase.notes.get_local_notes

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class GetLocalLastNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getLastLocalNote(): LocalNoteVO {
        return noteRepository.getLastLocalNote()
            .mapCatching { note ->
                note.let { domainToUiMapper.run { it.toViewObject() } }
            }
            .getOrElse { error ->
                Log.e(
                    "GetLocalLastNoteUseCase",
                    "Ошибка при загрузке последней заметки: ${error.message}"
                )
                throw error
            }
    }
}