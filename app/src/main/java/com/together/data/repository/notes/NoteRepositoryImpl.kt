package com.together.data.repository.notes

import android.util.Log
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.domain.models.CommunityNote
import com.together.domain.models.LocalNote
import com.together.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localNoteDao: LocalNoteDao,
    private val dataToDomainMapper: DataToDomainMapper,
    private val domainToDataMapper: DomainToDataMapper
) : NoteRepository {
    override suspend fun getCommunityNotes(): Result<List<CommunityNote>> {
        return runCatching {
            val response = apiService.getNotes()
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("NoteRepository", "Get community notes error: ${it.message}")
        }
    }

    override suspend fun getLastLocalNote(): Result<LocalNote> {
        return runCatching {
            val response = localNoteDao.getLastLocalNote()
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("NoteRepository", "Get last local note error: ${it.message}")
        }
    }

    override suspend fun getLocalNotes(): Result<List<LocalNote>> {
        return runCatching {
            val localNotes = localNoteDao.getLocalNotes()
            dataToDomainMapper.run { localNotes.toDomain() }
        }.onFailure {
            Log.e("NoteRepository", "Get local notes error: ${it.message}")
        }
    }

    override suspend fun postLocalNote(localNote: LocalNote): Result<Boolean> {
        return runCatching {
            val localNoteEntity = domainToDataMapper.run { localNote.toData() }
            localNoteDao.insertNote(localNoteEntity.localNote)
            localNoteEntity.content.forEach { contentEntity ->
                localNoteDao.insertContent(contentEntity)
            }
            true
        }.onFailure {
            Log.e("NoteRepository", "Post local note error: ${it.message}")
        }
    }

    override suspend fun getFavLocalNotes(): Result<List<LocalNote>> {
        return runCatching {
            val localNotes = localNoteDao.getFavouriteLocalNotes()
            dataToDomainMapper.run { localNotes.toDomain() }
        }.onFailure {
            Log.e("NoteRepository", "Get favourite local notes error: ${it.message}")
        }
    }
}