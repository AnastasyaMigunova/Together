package com.together.data.repository.courses

import android.util.Log
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.domain.models.Course
import com.together.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper,
    private val domainToDataMapper: DomainToDataMapper
) : CourseRepository {
    override suspend fun getCourses(): Result<List<Course>> {
        return runCatching {
            val response = apiService.getCourses()
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("CourseRepository", "Get courses error: ${it.message}")
        }
    }

    override suspend fun postCourse(course: Course): Result<Course> {
        return runCatching {
            val postCourseRequestDTO = domainToDataMapper.run { course.toData() }
            val response = apiService.postCourse(postCourseRequestDTO)
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("CourseRepository", "Post courses error: ${it.message}")
        }
    }

    override suspend fun getCourseById(courseId: String): Result<Course> {
        return runCatching {
            val response = apiService.getCourseById(courseId)
            dataToDomainMapper.run { response.toDomain() }
        }.onFailure {
            Log.e("CourseRepository", "Get course by id error: ${it.message}")
        }
    }
}