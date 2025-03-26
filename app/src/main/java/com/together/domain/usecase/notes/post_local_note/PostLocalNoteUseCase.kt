package com.together.domain.usecase.notes.post_local_note

import android.util.Log
import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.repository.NoteRepository
import com.together.ui.models.LocalNoteVO
import javax.inject.Inject

class PostLocalNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val uiToDomainMapper: UiToDomainMapper
) {
    suspend fun postLocalNote(localNoteVO: LocalNoteVO): Boolean {
        val localNote = uiToDomainMapper.run { localNoteVO.toDomain() }
        return noteRepository.postLocalNote(localNote)
            .fold(
                onSuccess = { true },
                onFailure = { error ->
                    Log.e("PostLocalNoteUseCase", "Ошибка при публикации локальной записи: ${error.message}")
                    false
                }
            )
    }
}
