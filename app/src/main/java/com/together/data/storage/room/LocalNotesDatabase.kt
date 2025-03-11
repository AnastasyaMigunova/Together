package com.together.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.data.storage.room.entity.ContentEntity
import com.together.data.storage.room.entity.LocalNoteEntity


@Database(
    entities = [LocalNoteEntity::class, ContentEntity::class],
    version = LocalNotesDatabase.VERSION,
)
abstract class LocalNotesDatabase : RoomDatabase() {

    abstract fun localNoteDao(): LocalNoteDao

    companion object {
        const val NAME = "app_db"
        const val VERSION = 1
    }
}