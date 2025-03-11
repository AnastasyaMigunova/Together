package com.together.domain.repository

import com.together.domain.models.Course

interface CourseRepository {
    suspend fun getCourses() : Result<List<Course>>

    suspend fun postCourse(course: Course) : Result<Course>

    suspend fun getCourseById(courseId: String) : Result<Course>
}