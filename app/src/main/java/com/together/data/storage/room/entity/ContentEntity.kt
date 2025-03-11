package com.together.data.storage.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContentEntity.TABLE_NAME)
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    val contentId: Long = 0,
    val noteId: String,
    val text: String?,
    val image: String
) {
    companion object {
        const val TABLE_NAME = "content"
    }
}