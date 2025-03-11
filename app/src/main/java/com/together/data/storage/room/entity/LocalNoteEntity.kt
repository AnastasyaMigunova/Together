package com.together.data.storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalNoteEntity.TABLE_NAME)
data class LocalNoteEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val date: String,
    val isFavourite: Boolean
) {
    companion object {
        const val TABLE_NAME = "local_notes"
    }
}