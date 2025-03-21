package com.together.data.repository.notes

import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.domain.models.Author
import com.together.domain.models.Comments
import com.together.domain.models.CommunityNote
import com.together.domain.models.Content
import com.together.domain.models.LocalNote
import com.together.domain.repository.NoteRepository
import javax.inject.Inject

class FakeNoteRepositoryImpl @Inject constructor(
) : NoteRepository {
    override suspend fun getCommunityNotes(): Result<List<CommunityNote>> {
        return Result.success(
            listOf(
                CommunityNote(
                    id = "1",
                    title = "Тестовая заметка сообщества",
                    content = listOf(Content(image = "", text = "Текст первой заметки")),
                    author = Author(
                        id = "1",
                        name = "Иван",
                        surname = "Петров",
                        avatar = "",
                        role = "Админ"
                    ),
                    comments = listOf(
                        Comments(
                            id = "1",
                            author = Author(id = "2", name = "Мария", surname = "Сидорова", avatar = "", role = "Студент"),
                            text = "Интересная заметка!"
                        )
                    ),
                    date = "2025-03-21T16:30:45Z"
                ),
                CommunityNote(
                    id = "2",
                    title = "Советы по Jetpack Compose",
                    content = listOf(
                        Content(image = "", text = "Используйте remember для сохранения состояния."),
                        Content(image = "", text = "Старайтесь минимизировать recomposition.")
                    ),
                    author = Author(
                        id = "3",
                        name = "Алексей",
                        surname = "Смирнов",
                        avatar = "",
                        role = "Админ"
                    ),
                    comments = listOf(
                        Comments(
                            id = "2",
                            author = Author(id = "4", name = "Елена", surname = "Иванова", avatar = "", role = "Студент"),
                            text = "Полезные советы, спасибо!"
                        ),
                        Comments(
                            id = "3",
                            author = Author(id = "5", name = "Владимир", surname = "Козлов", avatar = "", role = "Админ"),
                            text = "Еще бы примеры кода добавить."
                        )
                    ),
                    date = "2025-03-21T13:30:45Z"
                ),
                CommunityNote(
                    id = "3",
                    title = "Как работать с корутинами",
                    content = listOf(
                        Content(image = "", text = "Используйте viewModelScope для управления корутинами."),
                        Content(image = "", text = "Не забывайте обрабатывать исключения.")
                    ),
                    author = Author(
                        id = "6",
                        name = "Сергей",
                        surname = "Морозов",
                        avatar = "",
                        role = "Админ"
                    ),
                    comments = listOf(
                        Comments(
                            id = "4",
                            author = Author(id = "7", name = "Анна", surname = "Соколова", avatar = "", role = "Студент"),
                            text = "Очень полезно, спасибо!"
                        )
                    ),
                    date = "2025-03-21T17:30:45Z"
                )
            )
        )
    }

    override suspend fun getLastLocalNote(): Result<LocalNote> {
        return Result.success(
            LocalNote(
                id = "1",
                title = "Локальная заметка",
                content = listOf(
                    Content(image = "", text = "Пример контента локальной заметки 1."),
                    Content(image = "", text = "Пример контента локальной заметки 2.")
                ),
                date = "21 марта",
                isFavourite = true
            )
        )
    }

    override suspend fun getLocalNotes(): Result<List<LocalNote>> {
        TODO("Not yet implemented")
    }

    override suspend fun postLocalNote(localNote: LocalNote): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavLocalNotes(): Result<List<LocalNote>> {
        TODO("Not yet implemented")
    }

//    override suspend fun getLastLocalNote(): LocalNote? {
//        return try {
//            val response = localNoteDao.getLastLocalNote()
//            dataToDomainMapper.run { response?.toDomain() }
//        } catch (e: Exception) {
//            throw Exception("Get last local note error: ${e.message}", e)
//        }
//    }
//
//    override suspend fun getLocalNotes(): List<LocalNote> {
//        return try {
//            val localNotes = localNoteDao.getLocalNotes()
//            dataToDomainMapper.run { localNotes.toDomain() }
//        } catch (e: Exception) {
//            throw Exception("Get local notes error: ${e.message}", e)
//        }
//    }
//
//    override suspend fun postLocalNote(localNote: LocalNote): Boolean {
//        return try {
//            val localNoteEntity = domainToDataMapper.run { localNote.toData() }
//            localNoteDao.insertNote(localNoteEntity.localNote)
//            localNoteEntity.content.forEach { contentEntity ->
//                localNoteDao.insertContent(contentEntity)
//            }
//            true
//        } catch (e: Exception) {
//            throw Exception("Post local note error: ${e.message}", e)
//        }
//    }
//
//    override suspend fun getFavLocalNotes(): List<LocalNote> {
//        return try {
//            val localNotes = localNoteDao.getFavouriteLocalNotes()
//            dataToDomainMapper.run { localNotes.toDomain() }
//        } catch (e: Exception) {
//            throw Exception("Get favourite local notes error: ${e.message}", e)
//        }
//    }
}