package com.together.di

import android.content.Context
import androidx.room.Room
import com.together.data.storage.room.LocalNotesDatabase
import com.together.data.storage.room.dao.LocalNoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalNotesDatabase {
        return Room.databaseBuilder(
            context,
            LocalNotesDatabase::class.java,
            LocalNotesDatabase.NAME
        ).build()
    }

    @Provides
    fun provideLocalNoteDao(database: LocalNotesDatabase): LocalNoteDao {
        return database.localNoteDao()
    }
}