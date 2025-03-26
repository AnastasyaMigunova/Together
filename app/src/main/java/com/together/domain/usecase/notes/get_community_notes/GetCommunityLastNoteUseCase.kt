package com.together.domain.usecase.notes.get_community_notes

import android.util.Log
import com.together.data.repository.notes.FakeNoteRepositoryImpl
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.CommunityNoteVO
import javax.inject.Inject

class GetCommunityLastNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getLastCommunityNote(): CommunityNoteVO {
        return noteRepository.getCommunityNotes()
            .mapCatching { notes ->
                notes.maxByOrNull { it.date }
                    ?.let { domainToUiMapper.run { it.toViewObject() } }
                    ?: throw NoSuchElementException("Нет доступных записей")
            }
            .getOrElse { error ->
                Log.e(
                    "GetCommunityLastNoteUseCase",
                    "Ошибка при загрузке последней заметки: ${error.message}"
                )
                throw error
            }
    }
}
