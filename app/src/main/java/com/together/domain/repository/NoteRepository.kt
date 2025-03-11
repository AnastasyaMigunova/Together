package com.together.domain.repository

import com.together.domain.models.CommunityNote
import com.together.domain.models.LocalNote

interface NoteRepository {
    suspend fun getCommunityNotes() : Result<List<CommunityNote>>
    suspend fun getLastLocalNote(): Result<LocalNote>
    suspend fun getLocalNotes(): Result<List<LocalNote>>
    suspend fun postLocalNote(localNote: LocalNote): Result<Boolean>
    suspend fun getFavLocalNotes(): Result<List<LocalNote>>
}