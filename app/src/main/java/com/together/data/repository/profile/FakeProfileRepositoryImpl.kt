package com.together.data.repository.profile

import androidx.compose.ui.res.stringResource
import com.together.R
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.domain.models.Author
import com.together.domain.models.Comments
import com.together.domain.models.CommunityNote
import com.together.domain.models.Content
import com.together.domain.models.Course
import com.together.domain.models.Profile
import com.together.domain.models.Text
import com.together.domain.repository.ProfileRepository
import javax.inject.Inject

class FakeProfileRepositoryImpl @Inject constructor(
): ProfileRepository {
    override suspend fun getProfile(): Result<Profile> {
        return Result.success(
            Profile(
                id = "1",
                name = "Анастасия",
                surname = "Мигунова",
                avatar = "",
                role = 0,
                phone = "79063199071",
                registerDate = "2025-03-21T16:30:45Z",
                courses = listOf(
                    Course(
                        id = "1",
                        title = "Jetpack Compose Basics",
                        description = "Learn the fundamentals of Jetpack Compose.",
                        tags = listOf("Android", "Jetpack", "UI", "Android Studio", "Composable"),
                        textSections = listOf(
                            Text(image = "", text = "Introduction to Compose"),
                            Text(image = "", text = "Composable functions"),
                            Text(image = "", text = "State management")
                        )
                    ),
                    Course(
                        id = "2",
                        title = "Kotlin Coroutines",
                        description = "Master asynchronous programming in Kotlin.",
                        tags = listOf("Kotlin", "Concurrency", "Coroutines"),
                        textSections = listOf(
                            Text(image = "", text = "Suspending functions"),
                            Text(image = "", text = "Flows and Channels"),
                            Text(image = "", text = "Coroutine scopes")
                        )
                    ),
                    Course(
                        id = "3",
                        title = "Android Architecture Components",
                        description = "Build scalable Android apps using best practices.",
                        tags = listOf("Android", "Architecture", "MVVM", "MVP", "MVC", "UI"),
                        textSections = listOf(
                            Text(image = "", text = "ViewModel & LiveData"),
                            Text(image = "", text = "Room Database"),
                            Text(image = "", text = "Navigation Component")
                        )
                    )
                ),
                notes = listOf(
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
        )
    }
}