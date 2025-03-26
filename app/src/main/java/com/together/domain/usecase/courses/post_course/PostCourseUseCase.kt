package com.together.domain.usecase.courses.post_course

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class PostCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository,
    private val uiToDomainMapper: UiToDomainMapper,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun postCourse(courseVO: CourseVO): CourseVO? {
        val course = uiToDomainMapper.run { courseVO.toDomain() }

        return courseRepository.postCourse(course)
            .fold(
                onSuccess = { courseVO -> domainToUiMapper.run { courseVO.toViewObject() } },
                onFailure = { error ->
                    Log.e("PostCourseUseCase", "Ошибка при публикации курса: ${error.message}")
                    throw error
                }
            )
    }
}