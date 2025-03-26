package com.together.data.repository.notes

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
                        Content(
                            image = "",
                            text = "Используйте remember для сохранения состояния."
                        ),
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
                        Content(
                            image = "",
                            text = "Используйте viewModelScope для управления корутинами."
                        ),
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
        return Result.success(
            listOf(
                LocalNote(
                    id = "1",
                    title = "Первая заметка",
                    content = listOf(
                        Content(image = "", text = "Содержание первой заметки"),
                        Content(image = "", text = "Дополнительный текст")
                    ),
                    date = "2025-03-24T10:00:00Z",
                    isFavourite = true
                ),
                LocalNote(
                    id = "2",
                    title = "Вторая заметка",
                    content = listOf(
                        Content(image = "", text = "Описание второй заметки"),
                        Content(image = "", text = "Ещё один абзац")
                    ),
                    date = "2025-03-23T15:30:00Z",
                    isFavourite = false
                ),
                LocalNote(
                    id = "3",
                    title = "Третья заметка",
                    content = listOf(
                        Content(image = "", text = "Контент третьей заметки"),
                        Content(image = "", text = "Доп. информация")
                    ),
                    date = "2025-03-22T08:45:00Z",
                    isFavourite = true
                )
            )
        )
    }

    override suspend fun postLocalNote(localNote: LocalNote): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavLocalNotes(): Result<List<LocalNote>> {
        TODO("Not yet implemented")
    }
}