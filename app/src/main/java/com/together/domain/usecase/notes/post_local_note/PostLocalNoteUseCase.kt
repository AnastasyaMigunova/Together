package com.together.domain.usecase.notes.post_local_note

import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class PostLocalNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val uiToDomainMapper: UiToDomainMapper
) {
    suspend fun postLocalNote(localNoteVO: LocalNoteVO): Result<Boolean> {
        return try {
            val localNote = uiToDomainMapper.run { localNoteVO.toDomain() }
            noteRepository.postLocalNote(localNote)
        } catch (e: Exception) {
            throw e
        }
    }
}