package com.together.data.storage.room

import android.content.Context
import androidx.room.Room
import com.together.data.storage.room.dao.LocalNoteDao

object DatabaseClient {
    private var db: LocalNotesDatabase? = null

    fun noteDao(context: Context): LocalNoteDao {
        return getInstance(context).localNoteDao()
    }

    private fun getInstance(context: Context): LocalNotesDatabase {
        return db ?: run {
            val db = Room.databaseBuilder(
                context,
                LocalNotesDatabase::class.java,
                LocalNotesDatabase.NAME
            )
                .build()
            this.db = db
            db
        }
    }
}