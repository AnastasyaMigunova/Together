package com.together.domain.usecase.notes.get_local_notes

import android.util.Log
import com.together.data.repository.notes.FakeNoteRepositoryImpl
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
            .fold(
                onSuccess = { note ->
                    note.let { domainToUiMapper.run { it.toViewObject() } }
                },
                onFailure = { error ->
                    Log.e(
                        "GetLocalLastNoteUseCase",
                        "Ошибка при загрузке последней заметки: ${error.message}"
                    )
                    throw error
                }
            )
    }
}