package com.together.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.together.data.storage.room.entity.ContentEntity
import com.together.data.storage.room.entity.LocalNoteEntity
import com.together.data.storage.room.entity.NoteWithContent

@Dao
interface LocalNoteDao {
    @Insert
    suspend fun insertNote(localNote: LocalNoteEntity)

    @Insert
    suspend fun insertContent(content: ContentEntity)

    @Query("SELECT * FROM ${LocalNoteEntity.TABLE_NAME} WHERE id = :localNoteId")
    suspend fun getLocalNote(localNoteId: String): NoteWithContent

    @Query("SELECT * FROM ${LocalNoteEntity.TABLE_NAME}")
    suspend fun getLocalNotes(): List<NoteWithContent>


    @Query("SELECT * FROM ${LocalNoteEntity.TABLE_NAME} ORDER BY date DESC LIMIT 1")
    suspend fun getLastLocalNote(): NoteWithContent

    @Query("SELECT * FROM ${LocalNoteEntity.TABLE_NAME} WHERE isFavourite = 1")
    suspend fun getFavouriteLocalNotes(): List<NoteWithContent>
}