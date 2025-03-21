package com.together.data.repository.courses

import com.together.domain.models.Course
import com.together.domain.models.Text
import com.together.domain.repository.CourseRepository
import javax.inject.Inject

class FakeCourseRepositoryImpl @Inject constructor(
) : CourseRepository {
    override suspend fun getCourses(): Result<List<Course>> {
        return Result.success(
            listOf(
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
                ),
                Course(
                    id = "4",
                    title = "Firebase for Android",
                    description = "Integrate Firebase services into your Android app.",
                    tags = listOf("Firebase", "Backend", "Cloud"),
                    textSections = listOf(
                        Text(image = "", text = "Firestore Database"),
                        Text(image = "", text = "Firebase Authentication"),
                        Text(image = "", text = "Cloud Messaging (FCM)")
                    )
                ),
                Course(
                    id = "5",
                    title = "Jetpack Compose Advanced",
                    description = "Take your Jetpack Compose skills to the next level.",
                    tags = listOf("Android", "Jetpack", "UI"),
                    textSections = listOf(
                        Text(image = "", text = "Custom layouts"),
                        Text(image = "", text = "Animations in Compose"),
                        Text(image = "", text = "Performance optimization")
                    )
                )
            )
        )
    }

    override suspend fun postCourse(course: Course): Result<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseById(courseId: String): Result<Course> {
        TODO("Not yet implemented")
    }
}