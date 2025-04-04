package com.together.domain.usecase.notes.get_community_notes

import android.util.Log
import com.together.data.repository.notes.FakeNoteRepositoryImpl
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.CommunityNoteVO
import javax.inject.Inject

class GetCommunityNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getAllCommunityNotes(): List<CommunityNoteVO> {
        return noteRepository.getCommunityNotes()
            .fold(
                onSuccess = { notes ->
                    notes.map { note -> domainToUiMapper.run { note.toViewObject() } }
                },
                onFailure = { error ->
                    Log.e("GetCommunityNotesUseCase", "Ошибка при загрузке заметок: ${error.message}")
                    throw error
                }
            )
    }
}